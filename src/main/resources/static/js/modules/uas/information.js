$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'user/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {label: '资讯标题', name: 'name', index: 'name', width: 80},
            {label: '资讯正文', name: 'email', index: 'email', width: 80},
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80, formatter: getMyDateTime},
            {label: '修改时间', name: 'createTime', index: 'create_time', width: 80, formatter: getMyDateTime},
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
        info: {},
        q: {}
    },
    methods: {
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.info = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            // vm.getInfo(id)
        },
    }
});
