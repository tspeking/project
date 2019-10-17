$(function () {
    var $quotaTableForm = $(".corpTrend-table-form");
    var settings = {
        url: ctx + "quota/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                corpName: $quotaTableForm.find("input[name='corpName']").val(),
                quotaType: $quotaTableForm.find("select[name='quotaType']").val(),
                sort: "asc"
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'quotaId',
            visible: false
        }, {
            field: 'corpName',
            title: '企业名称'
        }, {
            field: 'quotaName',
            title: '指标名称'
        }, {
            field: 'quotaValue',
            title: '指标值'
        }, {
            field: 'quotaType',
            title: '指标类型',
            formatter: function (value, row, index) {
                if (value === '1') return '季度';
                else if (value === '2') return '年度';
                else return '未知';
            }
        },{
            field: 'quotaTime',
            title: '指标时间'
        },{
            field: 'createTime',
            title: '创建时间'
        }

        ]
    };

    $MB.initTable('corpTrendTable', settings);
});

function search() {
    $MB.refreshTable('corpTrendTable');
}

function refresh() {
    $(".corpTrend-table-form")[0].reset();
    $MB.refreshTable('corpTrendTable');
}

var $quotaAddForm = $("#corpTrend-add-form");
$("#corpTrend-add .btn-save").click(function () {
	var name = $(this).attr("name");
	var validator = $quotaAddForm.validate();
    var flag = validator.form();
    
    if (flag) {
    	if (name === "save") {
            $.post(ctx + "quota/add", $quotaAddForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("corpTrendTable");
                } else $MB.n_danger(r.msg);
            });
        } else if (name === "update") {
            $.post(ctx + "quota/update", $quotaAddForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("corpTrendTable");
                } else $MB.n_danger(r.msg);
            });
        }
    }
});

$("#corpTrend-add .btn-close").click(function () {
    closeModal();
});

function closeModal() {
	$("#corpTrend-add-button").attr("name", "save");
	$("#corpTrend-add-modal-title").html('新增企业指标信息');
    $MB.closeAndRestModal("corpTrend-add");
}

function deleteCorpTrends() {
    var selected = $("#corpTrendTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的数据！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].quotaId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中数据？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'quota/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function updateCorpTrend() {
    var selected = $("#corpTrendTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的数据！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一条数据！');
        return;
    }
    var quotaId = selected[0].quotaId;
    $.post(ctx + "quota/getCorpTrend", {"quotaId": quotaId}, function (r) {
        if (r.code === 0) {
            var $form = $('#corpTrend-add');
            $form.modal();
            var corpTrend = r.msg;
            $("#corpTrend-add-modal-title").html('修改企业指标信息');
            $form.find("input[name='corpName']").val(corpTrend.corpName);
            $form.find("input[name='quotaId']").val(corpTrend.quotaId);
            $form.find("select[name='quotaName']").val(corpTrend.quotaName);
            $form.find("input[name='quotaValue']").val(corpTrend.quotaValue);
            $form.find("select[name='quotaType']").val(corpTrend.quotaType);
            $form.find("input[name='quotaTime']").val(corpTrend.quotaTime);
            $("#corpTrend-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

