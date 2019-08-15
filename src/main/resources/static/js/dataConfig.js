/**
 *
 *表格公共配置
 */
var tableConfig = function (obj) {
    objConfig = {
        elem: obj.elem,
        url: obj.url, //数据接口
        request: {
            pageName: 'pageNum', //页码的参数名称，默认：page
            limitName: 'pageSize' //每页数据量的参数名，默认：limit
        },
        page: true, //开启分页
        loading: true,
        toolbar: true,
        unresize: true,
        height: 'full-125',
        text: {
            none: '暂无相关数据!'
        },
        cellMinWidth: 250,
        parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.resultCode, //解析接口状态
                "msg": res.resultMsg, //解析提示文本
                "count": res.resultContent.total, //解析数据长度
                "data": res.resultContent.list //解析数据列表
            };
        },
        page: true,
        cols: obj.cols
    };
    return $.extend(objConfig, obj);
}