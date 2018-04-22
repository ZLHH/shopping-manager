$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'orderId', index: 'order_id', width: 50, key: true},
            {label: '买家名字', name: 'name', index: 'name', width: 80},
            {label: '买家电话', name: 'email', index: 'email', width: 80},
            {label: '买家地址', name: 'nickName', index: 'nick_name', width: 80},
            {label: '买家微信openid', name: 'phoneNumber', index: 'phone_number', width: 80},
            {label: '订单总金额', name: 'role', index: 'role', width: 80,formatter:showRole},
            {label: '订单状态', name: 'role', index: 'role', width: 80,formatter:showRole},
            {label: '支付状态', name: 'role', index: 'pay_status', width: 80,formatter:showRole},
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: getMyDateTime},
            {label: '修改时间', name: 'updateTime', index: 'update_time', width: 80, formatter: getMyDateTime}
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
        order: {},
        q: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.order = {};
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
        forbidden: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('确定要禁用所选的用户？', function () {
                console.log("id = " + id)
                $.ajax({
                    type: "POST",
                    url: baseURL + "user/forbidden",
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
        recover: function () {
            var id = getSelectedRow();

            confirm('确定要恢复所选的用户？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "order/recover",
                    data:  {
                        "id": id
                    },
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
            var url = vm.order.id == null ? "order/save" : "order/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.order),
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
                    url: baseURL + "order/delete",
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
            $.get(baseURL + "order/info/" + id, function (r) {
                vm.order = r.order;
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
        return "<span class='label label-success'>正常</span>";
    else if (abled == 1)
        return "<span class='label label-danger'>禁用</span>";
    // else if (abled == 2)
    //     return "<span class='label label-warning'></span>";
}

function showRole(abled) {
    if (abled == 0)
        return "<span class='label label-success'>用户</span>";
    else if (abled == 1)
        return "<span class='label label-danger'>管理员</span>";
    // else if (abled == 2)
    //     return "<span class='label label-warning'></span>";
}