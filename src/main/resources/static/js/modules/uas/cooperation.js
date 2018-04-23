$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'coperation/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {label: '名字', name: 'name', index: 'name', width: 80},
            {label: '电话', name: 'phone', index: 'phone', width: 80},
            {label: '时间', name: 'time', index: 'time', width: 80},
            {label: '留言', name: 'message', index: 'message', width: 80},
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: showTime},
            {label: '修改时间', name: 'updateTime', index: 'update_time', width: 80, formatter: showTime},
            {label: '状态', name: 'status', index: 'status', width: 80, formatter: showabled},
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        coperation: {},
        q: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.coperation = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        changeStatus: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('确定要禁用所选的用户？', function () {
                console.log("id = " + id)
                $.ajax({
                    type: "POST",
                    url: baseURL + "coperation/changeStatus",
                    data: {
                        "id": id
                    } ,
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },

        saveOrUpdate: function (event) {
            var url = vm.coperation.id == null ? "coperation/save" : "coperation/update";
            console.log(vm.coperation);
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.coperation),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            console.log(ids);
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "coperation/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            console.log(id);
            $.get(baseURL + "coperation/info/" + id, function (r) {
                vm.coperation = r.coperation;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            console.log(vm.q);
            $("#jqGrid").jqGrid('setGridParam', {
                page: page,
                postData: vm.q
            }).trigger("reloadGrid");
        }
    }
});

function showabled(abled) {
    if (abled == 0)
        return "<span class='label label-danger'>未处理</span>";
    else if (abled == 1)
        return "<span class='label label-success'>已处理</span>";
}

function showRole(abled) {
    if (abled == 0)
        return "<span class='label label-success'>用户</span>";
    else if (abled == 1)
        return "<span class='label label-danger'>管理员</span>";
}

function showTime(cellvalue, options, rowObject) {
    console.log(cellvalue);
    if (cellvalue!=null){
        var oYear = cellvalue.year,
            oMonth = cellvalue.monthValue,
            oDay = cellvalue.dayOfMonth,
            oHour = cellvalue.hour,
            oMin = cellvalue.minute,
            oSen = cellvalue.second,
            oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);
        return oTime;
    }
    return "";

}