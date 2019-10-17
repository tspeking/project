$(function () {
    var $corpTableForm = $(".corp-table-form");
    var settings = {
        url: ctx + "corp/list",
        pageSize: 25,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                industry: $corpTableForm.find("input[name='industry']").val(),
                subIndustry: $corpTableForm.find("input[name='sub_industry']").val(),
                corpName: $corpTableForm.find("input[name='corp_name']").val(),
                fowardPrice: $corpTableForm.find("input[name='foward_price']").val(),
                sort: "asc"
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'corpId',
            visible: false
        }, {
            field: 'industry',
            title: '行业'
        }, {
            field: 'subIndustry',
            title: '细分行业'
        }, {
            field: 'corpName',
            title: '企业名称'
        }, {
            field: 'industryLocation',
            title: '产业位置',
            formatter: function (value, row, index) {
                if (value === '1') return '上游';
                else if (value === '2') return '中游';
                else if (value === '3') return '下游';
                else if (value === '4') return '上游、中游';
                else if (value === '5') return '中游、下游';
                else return '未知';
            }
        },{
            field: 'isFlagship',
            title: '是否龙头',
            formatter: function (value, row, index) {
                if (value === '1') return '是';
                else if (value === '2') return '否';
                else return '未知';
            }
        },{
            field: 'isPricePower',
            title: '是否定价权',
            formatter: function (value, row, index) {
                if (value === '1') return '是';
                else if (value === '2') return '否';
                else return '未知';
            }
        },{
            field: 'underestimateRegion',
            title: '低估区间'
        },{
            field: 'centreRegion',
            title: '中枢区间'
        },{
            field: 'premiumPrice',
            title: '溢价价格'
        },{
            field: 'fowardPrice',
            title: '估值'
        },{
            field: 'isUnderestimate',
            title: '是否低估',
            formatter: function (value, row, index) {
                if (value === '1') return '是';
                else if (value === '2') return '否';
                else return '未知';
            }
        },{
            field: 'priorityLevel',
            title: '优先级'
        },{
            field: 'marketShare',
            title: '市场份额'
        },{
            field: 'createTime',
            title: '创建时间'
        },{
            field: '',
            title: '操作',
            formatter: function (value, row, index) {
            	var index = row.corpId;
                return '<button id="corpDetail" type="button" onclick="detail('+index+')" class="btn btn-default">详情</button>';
            }
        }

        ]
    };

    $MB.initTable('corpTable', settings);
});

function search() {
    $MB.refreshTable('corpTable');
}

function refresh() {
    $(".corp-table-form")[0].reset();
    $MB.refreshTable('corpTable');
}

var $corpAddForm = $("#corp-add-form");
$("#corp-add .btn-save").click(function () {
	var name = $(this).attr("name");
	var validator = $corpAddForm.validate();
    var flag = validator.form();
    
    if (flag) {
    	if (name === "save") {
            $.post(ctx + "corp/add", $corpAddForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("corpTable");
                } else $MB.n_danger(r.msg);
            });
        } else if (name === "update") {
            $.post(ctx + "corp/update", $corpAddForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("corpTable");
                } else $MB.n_danger(r.msg);
            });
        }
    }
});

$("#corp-add .btn-close").click(function () {
    closeModal();
});

function closeModal() {
	$("#corp-add-button").attr("name", "save");
	$("#corp-add-modal-title").html('新增企业信息');
    $MB.closeAndRestModal("corp-add");
}

function deletecorps() {
    var selected = $("#corpTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的数据！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].corpId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中数据？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'corp/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function updatecorp() {
    var selected = $("#corpTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的数据！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一条数据！');
        return;
    }
    var corpId = selected[0].corpId;
    $.post(ctx + "corp/getCorp", {"corpId": corpId}, function (r) {
        if (r.code === 0) {
            var $form = $('#corp-add');
            $form.modal();
            var corp = r.msg;
            $("#corp-add-modal-title").html('修改企业信息');
            $form.find("input[name='industry']").val(corp.industry);
            $form.find("input[name='corpId']").val(corp.corpId);
            $form.find("input[name='subIndustry']").val(corp.subIndustry);
            $form.find("input[name='corpName']").val(corp.corpName);
            $form.find("select[name='industryLocation']").val(corp.industryLocation);
            $form.find("select[name='isFlagship']").val(corp.isFlagship);
            $form.find("select[name='isPricePower']").val(corp.isPricePower);
            $form.find("textarea[name='businessScope']").val(corp.businessScope);
            $form.find("textarea[name='marketShare']").val(corp.marketShare);
            $form.find("input[name='fowardPrice']").val(corp.fowardPrice);
            $form.find("select[name='isUnderestimate']").val(corp.isUnderestimate);
            $form.find("textarea[name='description']").val(corp.description);
            $form.find("textarea[name='remark']").val(corp.remark);
            $form.find("input[name='underestimateRegion']").val(corp.underestimateRegion);
            $form.find("input[name='centreRegion']").val(corp.centreRegion);
            $form.find("input[name='priorityLevel']").val(corp.priorityLevel);
            $form.find("input[name='premiumPrice']").val(corp.premiumPrice);
            $("#corp-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function detail(corpId){
    $.post(ctx + "corp/getCorp", {"corpId": corpId}, function (r) {
        if (r.code === 0) {
            var $form = $('#corp-detail');
            $form.modal();
            var corp = r.msg;
            $form.find("input[name='industry']").val(corp.industry);
            $form.find("input[name='subIndustry']").val(corp.subIndustry);
            $form.find("input[name='corpName']").val(corp.corpName);
            $form.find("select[name='industryLocation']").val(corp.industryLocation);
            $form.find("select[name='isFlagship']").val(corp.isFlagship);
            $form.find("select[name='isPricePower']").val(corp.isPricePower);
            $form.find("textarea[name='businessScope']").val(corp.businessScope);
            $form.find("textarea[name='marketShare']").val(corp.marketShare);
            $form.find("input[name='fowardPrice']").val(corp.fowardPrice);
            $form.find("select[name='isUnderestimate']").val(corp.isUnderestimate);
            $form.find("textarea[name='description']").val(corp.description);
            $form.find("textarea[name='remark']").val(corp.remark);
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

$("#corp-detail .btn-close").click(function () {
	closeModalDetail();
});

function closeModalDetail() {
    $MB.closeAndRestModal("corp-detail");
}

