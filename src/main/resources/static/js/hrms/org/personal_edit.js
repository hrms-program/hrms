//上报
function report() {
    if ($("#personnel_id").val() == "") {
        layer.msg("请保存后再上报!");
    } else {
        $.ajax({
            url: '/hrms/report/infor/reportPersonal', //用于文件上传的服务器端请求地址
            type: "POST",
            dataType: 'JSON', //返回值类型 一般设置为json
            data: { "personnelId": $("#personnel_id").val() }, //返回值类型 一般设置为json
            async: false,
            success: function(data) { //服务器成功响应处理函数
                console.log(data);
                if (data.status == 1) {
                    layer.msg(data.message)
                } else {
                    layer.alert(data.message);
                }
            },
            error: function(data) { //服务器响应失败处理函数
                layer.alert("网络异常，请退出登录后再重试！");
            }
        });
    }
}

var ModelContainer = [];
var Model = function(name, column, isList) {
    var that = this;
    that.name = name;
    that.editBtn = $("#" + name + "_edit_btn");
    that.addBtn = $("#" + name + "_add_btn");
    that.status = "view";
    that.viewBody = $("#" + name + "_view");
    that.editBody = $("#" + name + "_edit");
    that.formSubmitBtn = $("#" + name + "_form_submit_btn");
    that.formCancelBtn = $("#" + name + "_form_cancel_btn");
    that.formBody = $("#" + name + "_form");

    that.editBtn.click(function() {
        that.toEdit();
    });

    that.addBtn.click(function() {
        that.toAdd();
    });

    that.formCancelBtn.click(function() {
        that.toView();
    });

    that.isList = isList ? true : false;
    that.columns = column;

    // 附件列
    that.attachmentColumn = null;

    that.dependency = {};

    if (that.columns) {
        that.columns.forEach(function(column) {
            if (column.inputType === 'ATTACHMENT') {
                that.attachmentColumn = column;
            }

            if (column.dependency) {
                that.dependency[column.name] = [{
                    target: column.name,
                    dependColumn: column.dependency[0],
                    dependValue: column.dependency.slice(1, column.dependency.length + 1)
                }];
            }
        });

        for (var o in that.dependency) {
            var d = that.dependency[o];
            var fd = d[0].dependColumn;

            var p = that.dependency[fd];
            while (p) {
                d.push(p[0]);
                p = that.dependency[p[0].dependColumn];
            }
        }
    }

    // 创建表单提交
    if (that.formBody) {
        that.formBody.createForm({
            beforeCallback: function(formData) {

                if (that.name != 'base' && !personnelId) {
                    $.errorMessage("请先编辑人员基础信息");
                    return false;
                }

                if (personnelId) {
                    formData.push({
                        name: that.isList ? "personnelId" : "id",
                        value: personnelId,
                        type: "text",
                        required: false
                    });
                }

                if (that.attachmentColumn) {
                    var fileCount = 0;

                    // 有附件时，需要替换某些参数
                    var previews = that.inputAttachment.fileinput('getPreview');
                    var attachments = "";
                    if (previews && previews.config && previews.config.length > 0) {
                        previews.config.forEach(function(p) {
                            attachments += p.key + ",";
                            fileCount++;
                        });
                    }

                    // 动态加入已经上传的附件ID
                    formData.push({
                        name: that.attachmentColumn.name,
                        value: attachments,
                        type: "text",
                        required: false
                    });

                    // 原表单文件数据只有最后一个，这里需要手动从插件中获取File Object添加到表单数据中
                    var i = 0;

                    for (; i < formData.length; i++) {
                        if (formData[i].name == that.attachmentColumn.fileName) {
                            break;
                        }
                    }

                    formData.splice(i, 1);

                    var files = that.inputAttachment.fileinput('getFileStack');
                    if (files) {
                        files.forEach(function(file) {
                            formData.push({
                                name: that.attachmentColumn.fileName,
                                value: file,
                                type: "file",
                                required: false
                            });
                            fileCount++;
                        });
                    }

                    if (fileCount > 4) {
                        $.errorAlert("附件数量不能超过4个");
                        return false;
                    }
                }
            },
            successCallback: function(data) {
                if (that.name == 'base') {
                    personnelId = data.id;
                }

                $.successMessage("保存成功");
                that.setData(data)
                that.toView();
            }
        });
    }

    that.initEditDependency();
    ModelContainer.push(that);
}

Model.prototype.isInDependencyValues = function(val, vals) {
    if (val != null && val != undefined && val !== "") {
        for (var i = 0; i < vals.length; i++) {
            if (val == vals[i]) {
                return true;
            }
        }
    }
    return false;
}

Model.prototype.isDependencySatisfy = function(dependencies, data) {
    if (!data) { return false; }

    for (var i = 0; i < dependencies.length; i++) {
        var dep = dependencies[i];
        if (!this.isInDependencyValues(data[dep.dependColumn], dep.dependValue)) {
            return false;
        }
    }
    return true;
}

/** 赋值 */
Model.prototype.setData = function(data) {
    var that = this;
    that.data = data;

    // 拆分组件
    that.itemCom = [];

    if (that.data) {

        // 如果列依赖不成立时，列数据应该为空
        for (var o in that.dependency) {
            var depends = that.dependency[o];
            var tar = depends[0].target;

            if (that.isList) {
                that.data.forEach(function(a) {
                    if (!that.isDependencySatisfy(depends, a)) {
                        a[tar] = null;
                    }
                });
            } else {
                if (!that.isDependencySatisfy(depends, that.data)) {
                    that.data[tar] = null;
                }
            }
        }

        if (that.attachmentColumn) {
            // 解析的附件
            var filename = that.attachmentColumn.fileName;
            if (that.isList) {
                that.data.forEach(function(a) {
                    a[filename] = $.parseAttachmentData(a[filename]);
                });
            } else {
                that.data[filename] = $.parseAttachmentData(that.data[filename]);
            }
        }

        if (that.isList) {
            that.data.forEach(function(item) {
                that.itemCom.push({ data: item });
            });
        } else {
            that.itemCom.push({ data: that.data, viewBody: that.viewBody });
        }
    }

    // 填显示的值
    if (that.isList) {
        that.fillListViewBody();
    } else {
        that.fillViewBody();
    }
}

Model.prototype.fillViewBody = function() {
    var that = this;
    var data = that.data;

    if(that.name == 'base') {
        if(data.profilePhotoFile) {
            $("#profileImg")[0].src = $.parseAttachmentData(data.profilePhotoFile).url;
        } else {
            $("#profileImg")[0].src = "/static/image/user.png";
        }
    }

    if (that.columns) {
        that.columns.forEach(function(column) {
            var p = that.viewBody.find("[name='" + column.name + "']");

            if (!p || p.length == 0 || column.inputType === 'ATTACHMENT') return;

            var v = that.getColumnValue(column, data);

            if (v) {
                p.removeClass("text-muted");
                p.text(v);
            } else {
                p.addClass("text-muted");
                p.text("无");
            }
        });

        that.fillAttachment();
        that.checkViewDependency(that.viewBody, data);
    }
}

Model.prototype.getColumnValue = function(column, data) {
    var v = data ? data[column.name] : null;

    if (column.inputType === 'SELECT') {
        return $.getConstantEnumValue(column.enum, v);
    } else if (column.inputType === 'TREE') {
        return getTreeConstantName(column.enum, v);
    } else if (column.inputType === 'DATE') {
        if (typeof v === 'number') {
            v = dateFormat(v);
        }
    }
    return v;
}

Model.prototype.fillListViewBody = function() {
    var that = this;
    if (that.viewBody && that.columns) {
        var l = that.itemCom.length;
        var html = "";
        for (var k = 0; k < that.itemCom.length; k++) {
            var item = that.itemCom[k];
            var itemData = item.data;

            var liHtml = '<li class="item form-horizontal" id="' + that.name + '_item_body_' + k + '">';
            var isCollapse = false;
            var m = 0;
            for (var i = 0; i < that.columns.length;) {
                if (m == 3) {
                    liHtml += '<div id="' + that.name + '_collapse_detail_' + k + '" style="display: none">';
                    isCollapse = true;
                }

                liHtml += '<div class="form-group">';

                for (var j = 2; j > 0 && i < that.columns.length;) {
                    var column = that.columns[i++];
                    if (column.inputType === 'ID') {
                        continue;
                    } else if (column.inputType === 'ATTACHMENT') {
                        if (j != 2) {
                            i--;
                            break;
                        } else {
                            liHtml +=
                                '<label for="' + column.name + '" class="col-sm-3 control-label">' + column.title + '：</label>' +
                                '<div name="' + column.name + '"></div>';
                            break;
                        }
                    } else if (column.colspan == 2) {
                        if (j == 2) {
                            j = 0;
                        } else {
                            i--;
                            break;
                        }
                    } else {
                        j--;
                    }

                    var value = that.getColumnValue(column, itemData);
                    liHtml += '<label for="' + column.name + '" class="col-sm-3 control-label">' + column.title + '：</label>';
                    if (value) {
                        liHtml += '<div class="col-sm-' + (column.colspan == 2 ? 9 : 3) + '"><p name="' + column.name + '" class="form-control-static description">' + value + '</p></div>';
                    } else {
                        liHtml += '<div class="col-sm-' + (column.colspan == 2 ? 9 : 3) + '"><p name="' + column.name + '" class="form-control-static description text-muted">无</p></div>';
                    }
                }

                m++;
                liHtml += "</div>";
            }

            // 折叠部分
            if (isCollapse) {
                liHtml += '</div>'
            }

            liHtml += '<div class="col-sm-12">';
            liHtml += '<div class="col-sm-4 col-sm-offset-4 text-center">';
            if (isCollapse) {
                liHtml += '<a id="' + that.name + '_collapse_btn_' + k + '" href="javascript:void(0)">展开详情<i class="glyphicon glyphicon-chevron-down"></i></a>;';
            }
            liHtml += '</div>'
            liHtml += '<div class="col-sm-4"><div class="pull-right">' +
                '<a id="' + that.name + '_edit_btn_' + k + '" href="javascript:void(0)" style="margin-right:20px"><i class="fa fa-edit"></i>编辑</a>' +
                '<a id="' + that.name + '_delete_btn_' + k + '" href="javascript:void(0)"><i class="fa fa-trash-o"></i>删除</a>' +
                '</div></div>';

            liHtml += '</div>';
            html += liHtml;
        }

        that.viewBody.html(html);
        for (var k = 0; k < that.itemCom.length; k++) {
            that.itemCom[k].viewBody = $('#' + that.name + '_item_body_' + k);
            that.itemCom[k].editBtn = $('#' + that.name + '_edit_btn_' + k);
            that.itemCom[k].deleteBtn = $('#' + that.name + '_delete_btn_' + k);

            that.itemCom[k].editBtn.click(function(index) {
                return function() {
                    that.toEdit(index);
                }
            }(k));

            that.itemCom[k].deleteBtn.click(function(index) {
                return function() {
                    that.toDelete(index);
                }
            }(k));

            // 检查依赖
            that.checkViewDependency(that.itemCom[k].viewBody, that.itemCom[k].data);
        }

        for (var k = 0; k < that.itemCom.length; k++) {
            var body = $("#" + that.name + "_collapse_detail_" + k);
            var btn = $("#" + that.name + "_collapse_btn_" + k);
            that.itemCom[k].collapse = that.createCollapse(body, btn);
        }

        that.fillAttachment();
    }
}

Model.prototype.createCollapse = function(collapseBody, collapseBtn) {
    if (collapseBody.length > 0 && collapseBtn.length > 0) {
        var collapse = {
            isCollapse: true,
            collapseBody: collapseBody,
            collapseBtn: collapseBtn,
            init: function() {
                var that = this;
                that.collapseIcon = that.collapseBtn.find("i");
                that.collapseBtn.click(function() {
                    if (that.isCollapse) {
                        that.collapseBody.slideDown();
                        // 按钮样式
                        if (that.collapseIcon.length > 0) {
                            that.collapseIcon.removeClass("glyphicon-chevron-down");
                            that.collapseIcon.addClass("glyphicon-chevron-up");
                        }
                        that.isCollapse = false;
                    } else {
                        that.collapseBody.slideUp();
                        // 按钮样式
                        if (that.collapseIcon.length > 0) {
                            that.collapseIcon.removeClass("glyphicon-chevron-up");
                            that.collapseIcon.addClass("glyphicon-chevron-down");
                        }
                        that.isCollapse = true;
                    }
                });
            }
        };
        collapse.init();
        return collapse;
    }
    return null;
}

Model.prototype.fillEditBody = function() {
    var that = this;
    var data = that.currentData;
    if (that.columns) {
        that.columns.forEach(function(column) {
            var input = that.editBody.find("[name='" + column.name + "']");
            var ov = data ? data[column.name] : null;
            var v = ov;

            if (!input || column.inputType === 'ATTACHMENT') return;

            if (column.inputType === 'TREE') {
                input.data("Tree_Select").setCurrent(getTreeConstant(column.enum, v));
                return;
            }

            if (column.inputType === 'SELECT') {
                v = $.getConstantEnumValue(column.enum, v);
            } else if (column.inputType === 'DATE') {
                if (typeof v === 'number') {
                    v = dateFormat(v);
                }
            }

            if (v) {
                if (column.inputType === 'SELECT') {
                    input.val(ov);
                } else {
                    input.val(v);
                }
            } else {
                if (input.is("SELECT")) {
                    input[0].options[0].selected = true;
                } else {
                    input.val("");
                }
            }

        });

        that.checkEditDependency();
    }
}

Model.prototype.toAdd = function() {
    var that = this;
    that.currentData = null;
    that.addBtn.hide();
    that.viewBody.hide();
    that.editBody.show();
    that.fillEditBody();
    that.initAttachmentUploader();
}

Model.prototype.toEdit = function(index) {
    var that = this;
    if (that.data) {
        that.currentData = that.isList ? that.data[index] : that.data;
    } else {
        that.currentData = null;
    }

    if (that.name == 'base') {
        if (personnelId) {
            $("#addBasePart").hide();
            $("#addBasePart").find("input,select").attr("disabled", "disabled");
            that.formBody.attr("action", "/hrms/org/personal/save/base/update");
        } else {
            $("#addBasePart").show();
            $("#addBasePart").find("input,select").removeAttr("disabled");
            that.formBody.attr("action", "/hrms/org/personal/save/base/add");
        }
    }

    that.editBtn.hide();
    that.viewBody.hide();
    that.editBody.show();
    that.fillEditBody();
    that.initAttachmentUploader();
}

Model.prototype.toView = function() {
    var that = this;
    that.editBtn.show();
    that.addBtn.show();
    that.viewBody.show();
    that.editBody.hide();
}

Model.prototype.getAttachment = function() {
    var that = this;
    if (that.attachmentColumn) {
        var attachments = [];
        that.itemCom.forEach(function(item) {
            var aids = item.data[that.attachmentColumn.name];
            if (aids) {
                attachments = attachments.concat(aids);
            }
        });
        return attachments;
    }
    return null;
}

Model.prototype.fillAttachment = function() {
    var that = this;
    if (that.attachmentColumn) {
        var name = that.attachmentColumn.name;
        that.itemCom.forEach(function(item) {
            var atts = item.data[that.attachmentColumn.fileName];
            if (atts) {
                // 方案1
                // var attDiv = that.viewBody.find('[name="' + name + '"]');
                // var html = '<label class="col-sm-3 control-label"><i class="icon fa fa-download"></i>附件下载：</label><div class="col-sm-6"><ul class="products-list product-list-in-box">';
                // for (var i = 0; i < atts.length; i++) {
                //     var b = atts[i];
                //     html += '<li class="item"><a target="_blank" href="' + b.url + '" download="' + b.filename + '">' + b.filename + '</a></li>';
                // }
                // html += "</ul></div>";
                // attDiv.html(html);

                // 方案2
                var attDiv = that.viewBody.find('[name="' + name + '"]');
                var html = '<ul class="mailbox-attachments clearfix">';
                for (var i = 0; i < atts.length; i++) {
                    var b = atts[i];
                    var k = b.filename.lastIndexOf(".");
                    var suffix = "";
                    if (k >= 0) {
                        suffix = b.filename.substring(k + 1).toLowerCase();
                    }

                    var header = "";
                    if (suffix == "jpeg" || suffix == "jpg" || suffix == "png" || suffix == "gif") {
                        header = '<span class="mailbox-attachment-icon has-img"><img src="' + b.url + '" alt="Attachment"></span>';
                    } else {
                        var iconMap = {
                            txt: "fa-file-text-o",
                            xls: "fa-file-excel-o",
                            xlsx: "fa-file-excel-o",
                            pdf: "fa-file-pdf-o",
                            doc: "fa-file-word-o",
                            docx: "fa-file-word-o",
                            rar: "fa-file-zip-o",
                            zip: "fa-file-zip-o"
                        }
                        var icon = iconMap[suffix] || "fa-file-o";
                        header = '<span class="mailbox-attachment-icon"><i class="fa ' + icon + '"></i></span>';
                    }
                    html +=
                        '<li>' + header +
                        '    <div class="mailbox-attachment-info">' +
                        '        <a target="_blank" href="' + b.url + '" class="mailbox-attachment-name"><i class="fa fa-camera"></i>' + b.filename + '</a>' +
                        '        <span class="mailbox-attachment-size">' + (Math.floor(b.size / 1024) + "KB") + '<a target="_blank" download="' + b.filename + '" href="' + b.url + '" class="btn btn-default btn-xs pull-right"><i class="fa fa-cloud-download"></i></a></span>' +
                        '    </div>' +
                        '</li>';
                }
                html += "</ul>";
                attDiv.html(html);
            }
        });
    }
}

Model.prototype.initAttachmentUploader = function(fileInput, files) {
    var that = this;
    if (that.attachmentColumn) {
        var name = that.attachmentColumn.fileName;
        var atts = that.currentData ? that.currentData[name] : null;
        var fileInput = that.formBody.find('[name="' + name + '"]');

        var initialPreview = [];
        var initialPreviewConfig = [];
        if (atts) {
            atts.forEach(function(att) {
                initialPreview.push(att.url);
                initialPreviewConfig.push({
                    caption: att.filename,
                    size: att.size,
                    //url:"/common/constant?code=sex",
                    key: att.id
                });
            });
        }

        if (that.inputAttachment) {
            that.inputAttachment.fileinput('destroy');
        }

        that.inputAttachment = $(fileInput).fileinput({
            language: 'zh',
            uploadUrl: '/common/upload/files',
            showUpload: false,
            layoutTemplates: {
                actionUpload: '' //去除上传预览缩略图中的上传图片；
            },
            uploadAsync: false,
            maxFileCount: 4,
            allowedFileExtensions: ["jpeg", "jpg", "png", "gif"],
            overwriteInitial: false,
            ajaxDelete: false, // 扩展定义配置，不进行后台删除操作
            initialPreview: initialPreview,
            initialPreviewAsData: true, // allows you to set a raw markup
            initialPreviewFileType: 'image', // image is the default and can be overridden in config below
            initialPreviewConfig: initialPreviewConfig
        });
    }
}

Model.prototype.checkViewDependency = function(container, data) {
    var that = this;
    for (var o in that.dependency) {
        var depends = that.dependency[o];
        var tar = depends[0].target;
        var div = container.find("[name='" + tar + "']").parent().parent();

        if (!that.isDependencySatisfy(depends, data)) {
            if (!div.hasClass("hidden-column")) {
                div.addClass("hidden-column");
            }
        } else {
            if (div.hasClass("hidden-column")) {
                div.removeClass("hidden-column");
            }
        }
    }
}

Model.prototype.checkEditDependency = function() {
    var that = this;
    for (var o in that.dependency) {
        var dependencies = that.dependency[o];
        var isOk = true;

        for (var i = 0; i < dependencies.length; i++) {
            var dep = dependencies[i];
            var val = that.editBody.find("[name='" + dep.dependColumn + "']").val();
            if (!that.isInDependencyValues(val, dep.dependValue)) {
                isOk = false;
                break;
            }
        }

        var input = that.editBody.find("[name='" + dependencies[0].target + "']");

        var div = input.parents(".form-group");
        if (isOk) {
            if (div.hasClass("hidden-column")) {
                div.removeClass("hidden-column");
            }
            input.removeAttr("disabled");
        } else {
            if (!div.hasClass("hidden-column")) {
                div.addClass("hidden-column");
            }
            input.attr("disabled", true);
            input.val("");
        }
    }
}

Model.prototype.initEditDependency = function() {
    var that = this;
    var cache = {};
    for (var o in that.dependency) {
        var depend = that.dependency[o][0];
        if (cache[depend.dependColumn]) {
            continue;
        }
        var select = that.editBody.find("[name='" + depend.dependColumn + "']");
        select.change(function() {
            if (that.name == "practice") {
                var self = $(this);
                //处理执业信息中 乡村医生特殊情况
                if (self.attr("name") == 'personnelType') {
                    var input = that.editBody.find("[name='isInVillageClinic']");
                    var div = input.parents(".form-group");

                    if (self.val() == '3') {
                        input.val("1");
                        if (!div.hasClass("hidden-column")) {
                            div.addClass("hidden-column");
                        }
                    } else {
                        input.val("");
                        if (div.hasClass("hidden-column")) {
                            div.removeClass("hidden-column");
                        }
                    }
                }
            }
            that.checkEditDependency();
        });
        cache[depend.dependColumn] = 1;
    }
}


var personnelId;
var personnel;
var treeData;

var baseModel;
var education;
var job;
var workExperience;
var positionalInfo;
var rewardInfo;
var cultivate;
var registrations;
var yearassess;
var scienceEducation;
var practice;

var isTreeConstantOk = false;
var isPersonnelOk = false;

$(function() {
    personnelId = $("#personnel_id").val();

    baseModel = new Model("base", baseModelColumns);
    job = new Model("job", jobModelModelColumns);
    practice = new Model("practice", practiceModelColumns);
    education = new Model("education", educationModelColumns, true);
    workExperience = new Model("workExperience", workExperienceModelColumns, true);
    positionalInfo = new Model("positionalInfo", positionalInfoModelColumns, true);
    rewardInfo = new Model("rewardInfo", rewardInfoModelColumns, true);
    cultivate = new Model("cultivate", cultiveteModelColumns, true);
    registrations = new Model("registrations", registrationsModelColumns, true);
    yearassess = new Model("yearassess", yearassessModelColumns, true);
    scienceEducation = new Model("scienceEducation", scienceEducationModelColumns, true);

    if (personnelId) {
        $.getAjax("/hrms/org/personal/detail?id=" + personnelId, function(data) {
            personnel = data;
            isPersonnelOk = true;
            initPersonnel();
        });
    } else {
        personnel = null;
        isPersonnelOk = true;
        initPersonnel();
    }

    $.getJSON("/static/js/hrms/org/personal_tree_constant.json", function(data) {
        treeData = data;
        initTreeConstant();
        isTreeConstantOk = true;
        initPersonnel();
    });
});

function initPersonnel() {
    if (isTreeConstantOk && isPersonnelOk) {
        if (personnel) {
            baseModel.setData(personnel.base);
            education.setData(personnel.educations);
            job.setData(personnel.job);
            practice.setData(personnel.practice);
            workExperience.setData(personnel.experience);
            positionalInfo.setData(personnel.positionalInfo);
            rewardInfo.setData(personnel.rewardInfo);
            cultivate.setData(personnel.cultivate);
            registrations.setData(personnel.registrations);
            yearassess.setData(personnel.yearassess);
            scienceEducation.setData(personnel.scienceEducation);
        } else {
            baseModel.setData(null);
            education.setData(null);
            job.setData(null);
            practice.setData(null);
            workExperience.setData(null);
            positionalInfo.setData(null);
            rewardInfo.setData(null);
            cultivate.setData(null);
            registrations.setData(null);
            yearassess.setData(null);
            scienceEducation.setData(null);
        }
    }
}

// 初始化树常量
function initTreeConstant() {
    var departmentTypes = treeData["department-type"];
    var technicalTypes = treeData["technical-qualification-type"];
    $.createTreeSelectComponment("#dept_select").setData(departmentTypes);
    $.createTreeSelectComponment("#technical_select").setData(technicalTypes);
}

function getTreeConstantName(type, val) {
    var data = treeData[type];
    if (val) {
        for (var i in data) {
            if (data[i].value == val) {
                return data[i].name;
            }
        }
    }
    return "";
}

function getTreeConstant(type, val) {
    var data = treeData[type];
    if (val) {
        for (var i = 0; i < data.length; i++) {
            if (data[i].value == val) {
                return data[i];
            }
        }
    }
    return null;
}


var baseModelColumns = [{ name: "id", inputType: "ID" }, { name: "identificationType", inputType: "SELECT", enum: "identification-type" }, { name: "identificationNo", inputType: "TEXT" }, { name: "name", inputType: "TEXT" }, { name: "usedName", inputType: "TEXT" }, { name: "sex", inputType: "SELECT", enum: "sex" }, { name: "cellphone", inputType: "TEXT" }, { name: "officePhone", inputType: "TEXT" }, { name: "profilePhoto", inputType: "TEXT" }, { name: "agencyId", inputType: "TEXT" }, { name: "agencyName", inputType: "TEXT" }, { name: "nationality", inputType: "SELECT", enum: "nationality" }, { name: "nation", inputType: "SELECT", enum: "nation" }, { name: "birthday", inputType: "DATE" }, { name: "startWorkTime", inputType: "DATE" }, { name: "joinPartyTime", inputType: "DATE" }, { name: "politicalAffiliation", inputType: "SELECT", enum: "political-affiliation" }, { name: "interest", inputType: "TEXT" }, { name: "nativePlace", inputType: "TEXT" }, { name: "status", inputType: "SELECT", enum: "peronnel-status" }];
var jobModelModelColumns = [{ name: "id", inputType: "ID" }, { name: "employeeNo", inputType: "TEXT" }, { name: "dept", inputType: "TREE", enum: "department-type" }, { name: "name", inputType: "TEXT" }, { name: "deptName", inputType: "TEXT" }, { name: "major", inputType: "SELECT", enum: "major" }, { name: "duty", inputType: "SELECT", enum: "duty" }, { name: "technicalQualification", inputType: "TREE", enum: "technical-qualification-type" }, { name: "gainDate", inputType: "DATE" }, { name: "techPost", inputType: "SELECT", enum: "tech-post" }, { name: "employDate", inputType: "DATE" }, { name: "employPost", inputType: "SELECT", enum: "employ-post" }, { name: "formation", inputType: "SELECT", enum: "formation" }, { name: "inorout", inputType: "SELECT", enum: "inorout" }, { name: "inoroutDate", inputType: "DATE" }, { name: "isStatistical", inputType: "SELECT", enum: "boolean" }, { name: "workContent", inputType: "SELECT", enum: "work-content", dependency: ["isStatistical", "1"] }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var practiceModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelType", inputType: "SELECT", enum: "personnel-type", colspan: 2 }, { name: "docQualificationCode", inputType: "TEXT", dependency: ["personnelType", "1"] }, { name: "docCertCode", inputType: "TEXT", dependency: ["personnelType", "1"] }, { name: "isCountryCert", inputType: "SELECT", enum: "boolean", dependency: ["personnelType", "1"], colspan: 2 }, { name: "inpatientCertCode", inputType: "TEXT", dependency: ["isCountryCert", "1"], colspan: 2 }, { name: "isGeneralPractitioner", inputType: "SELECT", enum: "boolean", dependency: ["personnelType", "1"], colspan: 2 }, { name: "practiceAddress", inputType: "TEXT", dependency: ["personnelType", "1"], colspan: 2 }, { name: "registrationDate", inputType: "DATE", dependency: ["personnelType", "1"] }, { name: "practiceCategory", inputType: "SELECT", dependency: ["personnelType", "1"], enum: "practice-category-type" }, { name: "practiceScope", inputType: "TEXT", dependency: ["personnelType", "1"], colspan: 2 }, { name: "isDispatched", inputType: "SELECT", enum: "boolean", dependency: ["personnelType", "1"], colspan: 2 }, { name: "isMultisite", inputType: "SELECT", enum: "boolean", dependency: ["personnelType", "1"], colspan: 2 }, { name: "secondCategory", inputType: "SELECT", enum: "practice-category", dependency: ["isMultisite", "1"] }, { name: "thirdCategory", inputType: "SELECT", enum: "practice-category", dependency: ["isMultisite", "1"] }, { name: "expertise", inputType: "TEXT", dependency: ["personnelType", "1"] }, { name: "docTrainCert", inputType: "TEXT", dependency: ["personnelType", "1"], colspan: 2 }, { name: "nurseCertCode", inputType: "TEXT", dependency: ["personnelType", "2"], colspan: 2 }, { name: "nurseInstitution", inputType: "TEXT", dependency: ["personnelType", "2"] }, { name: "lastRegistrationDate", inputType: "DATE", dependency: ["personnelType", "2"] }, { name: "isExam", inputType: "SELECT", enum: "boolean", dependency: ["personnelType", "0"], colspan: 2 }, { name: "nurseExamTime", inputType: "DATE", dependency: ["isExam", "0"], colspan: 2 }, { name: "startWorkDate", inputType: "DATE", dependency: ["personnelType", "2"] }, { name: "nurseCategory", inputType: "TEXT", dependency: ["personnelType", "2"] }, { name: "certName", inputType: "TEXT", dependency: ["personnelType", "4", "5", "6", "7", "8"] }, { name: "issueUnit", inputType: "TEXT", dependency: ["personnelType", "4", "5", "6", "7", "8"] }, { name: "issueDate", inputType: "DATE", dependency: ["personnelType", "4", "5", "6", "7", "8"] }, { name: "startDate", inputType: "DATE", dependency: ["personnelType", "4", "5", "6", "7", "8"] }, { name: "endDate", inputType: "DATE", dependency: ["personnelType", "4", "5", "6", "7", "8"], colspan: 2 }, { name: "isInVillageClinic", inputType: "SELECT", enum: "boolean" }, { name: "vdocCertCode", inputType: "TEXT", dependency: ["isInVillageClinic", "1"] }, { name: "registVillageAgency", inputType: "TEXT", dependency: ["isInVillageClinic", "1"] }, { name: "registDate", inputType: "DATE", dependency: ["isInVillageClinic", "1"] }, { name: "totalIncome", inputType: "TEXT", dependency: ["isInVillageClinic", "1"] }, { name: "isMedicalInsurance", inputType: "SELECT", enum: "boolean", dependency: ["isInVillageClinic", "1"] }, { name: "isInjuryInsurance", inputType: "SELECT", enum: "boolean", dependency: ["isInVillageClinic", "1"] }, { name: "isEndowmentInsurance", inputType: "SELECT", enum: "boolean", dependency: ["isInVillageClinic", "1"] }, { name: "workYears", inputType: "TEXT", dependency: ["isInVillageClinic", "1"] }, { name: "isOnjobTrain", inputType: "SELECT", enum: "boolean", dependency: ["isInVillageClinic", "1"] }];
var educationModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "schoolName", inputType: "TEXT", title: "学校名称" }, { name: "schoolAddress", inputType: "TEXT", title: "学校地址" }, { name: "education", inputType: "SELECT", enum: "education", title: "学历" }, { name: "diplomaNo", inputType: "TEXT", title: "毕业证书编号" }, { name: "academicDegree", inputType: "SELECT", enum: "academic-degree", title: "学位" }, { name: "academicDegreeNo", inputType: "TEXT", title: "学位证书编号" }, { name: "major", inputType: "SELECT", enum: "major-type", title: "所学专业" }, { name: "educationType", inputType: "SELECT", enum: "education-type", title: "教育类型" }, { name: "joinSchoolTime", inputType: "DATE", title: "入学时间" }, { name: "graduationTime", inputType: "DATE", title: "毕业时间" }, { name: "hasRecordStatus", inputType: "TEXT", title: "取得证书情况" }, { name: "otherInstructions", inputType: "TEXT", title: "补充说明" }, { name: "status", inputType: "SELECT", enum: "personal-info-status", title: "审核状态" }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var workExperienceModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "workUnit", inputType: "TEXT", title: "工作单位" }, { name: "workPlace", inputType: "TEXT", title: "工作地点" }, { name: "workStartTime", inputType: "DATE", title: "工作开始时间" }, { name: "workEndTime", inputType: "DATE", title: "工作结束时间" }, { name: "workDept", inputType: "SELECT", enum: "dept-type", title: "工作科室" }, { name: "jobSituation", inputType: "SELECT", enum: "duty", title: "任职情况" }, { name: "workPost", inputType: "SELECT", enum: "dept-type", title: "工作岗位" }, { name: "majorTechnologyWork", inputType: "SELECT", enum: "major", title: "从事专业技术工作" }, { name: "status", inputType: "SELECT", enum: "personal-info-status", title: "审核状态" }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var positionalInfoModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "positionalTitleName", inputType: "TEXT", title: "职称名称" }, { name: "positionalTitleType", inputType: "SELECT", enum: "positional-type", title: "职称类型" }, { name: "positionalLevelLevel", inputType: "SELECT", enum: "tech-post", title: "职称级别" }, { name: "positionalTitleTime", inputType: "DATE", title: "获得时间" }, { name: "status", inputType: "SELECT", enum: "personal-info-status", title: "审核状态" }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var scienceEducationModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "scienceEducationType", inputType: "SELECT", enum: "science-education-type", title: "种类", colspan: 2 }, { name: "name", inputType: "TEXT", title: "名称", colspan: 2 }, { name: "element", inputType: "TEXT", title: "要素", colspan: 2 }, { name: "topicNumber", inputType: "TEXT", title: "课题编号", dependency: ["scienceEducationType", "1"] }, { name: "topicSource", inputType: "TEXT", title: "课题项目来源", dependency: ["scienceEducationType", "1"] }, { name: "year", inputType: "TEXT", title: "年度", dependency: ["scienceEducationType", "1"] }, { name: "money", inputType: "TEXT", title: "金额", dependency: ["scienceEducationType", "1"] }, { name: "prizeIssuingOrgan", inputType: "TEXT", title: "奖项发证机关", dependency: ["scienceEducationType", "2"] }, { name: "prizeGrade", inputType: "TEXT", title: "奖项等级", dependency: ["scienceEducationType", "2"] }, { name: "awardProject", inputType: "TEXT", title: "授奖项目", dependency: ["scienceEducationType", "2"] }, { name: "awardTime", inputType: "DATE", title: "获奖时间", dependency: ["scienceEducationType", "2"] }, { name: "awardTopic", inputType: "TEXT", title: "奖项关联课题", colspan: 2, dependency: ["scienceEducationType", "2"] }, { name: "oneselfRanking", inputType: "TEXT", title: "本人排名", colspan: 2, dependency: ["scienceEducationType", "1", "2"] }, { name: "endTime", inputType: "DATE", title: "结题时间", dependency: ["scienceEducationType", "1"] }, { name: "dept", inputType: "TEXT", title: "立项部门", dependency: ["scienceEducationType", "1"] }, { name: "paperMagazine", inputType: "TEXT", title: "刊登论文杂志", dependency: ["scienceEducationType", "3"] }, { name: "sciInfluenceFactor", inputType: "TEXT", title: "SCI影响因子", dependency: ["scienceEducationType", "3"] }, { name: "paperFirstAuthor", inputType: "TEXT", title: "论文第一作者", dependency: ["scienceEducationType", "3"] }, { name: "paperCommunicationAuthor", inputType: "TEXT", title: "论文通讯作者", dependency: ["scienceEducationType", "3"] }, { name: "paperReleaseTime", inputType: "DATE", title: "论文发布时间", dependency: ["scienceEducationType", "3"] }, { name: "paperType", inputType: "SELECT", enum: "paper-type", title: "论文种类", dependency: ["scienceEducationType", "3"] }, { name: "paperRelationTopic", inputType: "SELECT", enum: "", title: "论文关联课题", dependency: ["scienceEducationType", "3"] }, { name: "status", inputType: "SELECT", enum: "personal-info-status", title: "审核状态", colspan: 2 }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var rewardInfoModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "rewardProject", inputType: "TEXT", title: "奖励项目" }, { name: "rewardLevel", inputType: "TEXT", title: "奖励级别" }, { name: "rewardType", inputType: "SELECT", enum: "reward_type", title: "获奖类别" }, { name: "rewardTime", inputType: "DATE", title: "奖励时间" }, { name: "awardPrizeDept", inputType: "TEXT", title: "授奖部门" }, { name: "isRevoke", inputType: "SELECT", enum: "boolean", title: "是否撤销" }, { name: "rewardMoney", inputType: "TEXT", title: "获奖金额" }, { name: "rewardReason", inputType: "TEXT", title: "获奖原因" }, { name: "rewardIllustrate", inputType: "TEXT", title: "奖励说明" }, { name: "status", inputType: "SELECT", enum: "personal-info-status", title: "审核状态" }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var cultiveteModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "cultivateType", inputType: "SELECT", enum: "cultivate-type", title: "培训类型" }, { name: "cultivateUnit", inputType: "TEXT", title: "培训单位" }, { name: "cultivatePlace", inputType: "TEXT", title: "培训地点" }, { name: "cultivateStartTime", inputType: "DATE", title: "开始时间" }, { name: "cultivateEndTime", inputType: "DATE", title: "结束时间" }, { name: "endSituation", inputType: "SELECT", enum: "cultivete-end-situation", title: "结束情况" }, { name: "status", inputType: "SELECT", enum: "personal-info-status", title: "审核状态" }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var registrationsModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "certificateCode", inputType: "TEXT", title: "执业证书编码" }, { name: "placeName", inputType: "TEXT", title: "执业地点名称" }, { name: "category", inputType: "SELECT", enum: "", title: "执业类别" }, { name: "issuingOrgan", inputType: "TEXT", title: "发证机关" }, { name: "businessType", inputType: "SELECT", enum: "", title: "业务类型" }, { name: "qualificationCertificateCode", inputType: "TEXT", title: "执业资格证书编号" }, { name: "range", inputType: "SELECT", enum: "", title: "执业范围" }, { name: "level", inputType: "SELECT", enum: "", title: "执业级别" }, { name: "firstRegisterTime", inputType: "DATE", title: "首次注册时间" }, { name: "practice_address", inputType: "TEXT", title: "执业地点地址" }, { name: "registerIDCArd", inputType: "TEXT", title: "执业注册身份证" }, { name: "address", inputType: "TEXT", title: "地点" }, { name: "districtCode", inputType: "TEXT", title: "行政区划代码" }, { name: "districtName", inputType: "TEXT", title: "行政区划名称" }, { name: "creationTime", inputType: "DATE", title: "创建时间" }, { name: "lastUpdateTime", inputType: "DATE", title: "最后更新时间" }, { name: "status", inputType: "SELECT", enum: "personal-info-status", title: "审核状态" }, { name: "attachments", inputType: "ATTACHMENT", fileName: "attachmentFiles", title: "附件" }];
var yearassessModelColumns = [{ name: "id", inputType: "ID" }, { name: "personnelId", inputType: "ID" }, { name: "year", inputType: "TEXT", title: "年度" }, { name: "wages", inputType: "TEXT", title: "当年工资(元)" }, { name: "bonus", inputType: "TEXT", title: "当年奖金(元)" }, { name: "otherIncome", inputType: "TEXT", title: "当年其他收入(元)" }, { name: "assessResult", inputType: "SELECT", enum: "assess-result", title: "考核结果" }, { name: "technicalPost", inputType: "TEXT", title: "受聘专业技术职务(岗位)" }, { name: "assessIllustrate", inputType: "TEXT", title: "考核说明" }];