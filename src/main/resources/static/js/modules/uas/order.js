$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'orderId', index: 'order_id', width: 80, key: true},
            {label: '买家名字', name: 'buyerName', index: 'buyer_name', width: 50},
            {label: '买家电话', name: 'buyerPhone', index: 'buyer_phone', width: 60},
            {label: '买家地址', name: 'buyerAddress', index: 'buyer_address', width: 80},
            {label: '买家微信openid', name: 'buyerOpenid', index: 'buyer_openid', width: 80},
            {label: '订单总金额', name: 'orderAmount', index: 'order_amount', width: 40},
            {label: '订单状态', name: 'orderStatus', index: 'order_status', width: 30,formatter:showOrder},
            {label: '支付状态', name: 'payStatus', index: 'pay_status', width: 30,formatter:showPay},
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: showTime},
            {label: '修改时间', name: 'updateTime', index: 'update_time', width: 80, formatter: showTime}
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
            confirm('确定要完成所选的订单？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "order/forbidden",
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
            var url = "order/update";
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

function showOrder(abled) {
    if (abled == 0)
        return "<span class='label label-info'>新下单</span>";
    else if (abled == 1)
        return "<span class='label label-warning'>修改订单</span>";
    else if (abled == 2)
        return "<span class='label label-success'>订单完成</span>";
    else if (abled == 3)
        return "<span class='label label-danger'>取消订单</span>";
}

function showPay(abled) {
    if (abled == 0)
        return "<span class='label label-warning'>未支付</span>";
    else if (abled == 1)
        return "<span class='label label-success'>已支付</span>";
    else if (abled == 2)
        return "<span class='label label-info'>退款中</span>";
    else if (abled == 3)
        return "<span class='label label-danger'>已退款</span>";
}

function showTime(cellvalue, options, rowObject) {
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