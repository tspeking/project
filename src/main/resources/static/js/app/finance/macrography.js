$(function () {
    var $macrographyTableForm = $(".macrography-table-form");
    var settings = {
        url: ctx + "macrography/lists",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                type: $macrographyTableForm.find("select[name='financeType']").val(),
                sort: "desc",
                startTime: "",
                endTime: ""
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'financeId',
            visible: false
        }, {
            field: 'quotaName',
            title: '日期'
        }, {
            field: 'quotaValue',
            title: '数值'
        },{
            field: 'type',
            title: '类型',
            formatter: function (value, row, index) {
                if (value === '1') return ' 融资余额';
                else if (value === '2') return 'CPI';
                else if (value === '3') return 'M2';
                else if (value === '4') return '社融 ';
                else if (value === '5') return 'GDP季度';
                else if (value === '6') return '乘用车同比';
                else if (value === '7') return '乘用车环比';
                else if (value === '8') return '沪深交易量';
                else if (value === '9') return '沪股通资金流入';
                else if (value === '10') return '深股通资金流入';
                else if (value === '11') return '北向资金';
                else return '未知';
            }
        }, {
            field: 'crateTime',
            title: '创建时间'
        }

        ]
    };

    $MB.initTable('macrographyTable', settings);
});

function search() {
    $MB.refreshTable('macrographyTable');
}

function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('macrographyTable');
}

var $macrographyAddForm = $("#macrography-add-form");
$("#macrography-add .btn-save").click(function () {
	var name = $(this).attr("name");
	var validator = $macrographyAddForm.validate();
    var flag = validator.form();
    
    if (flag) {
    	if (name === "save") {
            $.post(ctx + "macrography/add", $macrographyAddForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("macrographyTable");
                } else $MB.n_danger(r.msg);
            });
        } else if (name === "update") {
            $.post(ctx + "macrography/update", $macrographyAddForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeModal();
                    $MB.n_success(r.msg);
                    $MB.refreshTable("macrographyTable");
                } else $MB.n_danger(r.msg);
            });
        }
    }
});

$("#macrography-add .btn-close").click(function () {
    closeModal();
});

function closeModal() {
    $MB.closeAndRestModal("macrography-add");
}

function deleteMacrographys() {
    var selected = $("#macrographyTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的数据！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].financeId;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中数据？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'macrography/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function updateMacrography() {
    var selected = $("#macrographyTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的数据！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一条数据！');
        return;
    }
    var financeId = selected[0].financeId;
    $.post(ctx + "macrography/getMacrography", {"financeId": financeId}, function (r) {
        if (r.code === 0) {
            var $form = $('#macrography-add');
            $form.modal();
            var macrography = r.msg;
            $("#macrography-add-modal-title").html('修改数据');
            $form.find("input[name='quotaName']").val(macrography.quotaName);
            $form.find("input[name='financeId']").val(macrography.financeId);
            $form.find("input[name='quotaValue']").val(macrography.quotaValue);
            $form.find("select[name='type']").val(macrography.type);
            $("#macrography-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

