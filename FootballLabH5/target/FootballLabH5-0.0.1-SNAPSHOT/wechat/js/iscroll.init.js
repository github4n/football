var myScroll;
var showCount = 9;// 显示的条数
var currentPage = 0;// 当前页数-1
var dataUrl = "";// 请求数据使用的url
var dataParam;// 传递的参数，json
var isGetData = true;
var pagetop = 40;

// 加载数据
function updatePosition() {
    // 上拉加载数据
    if (this.y < (this.maxScrollY - pagetop)) {
        currentPage = parseInt(currentPage)+1;
        dataParam.currentPage = currentPage;
        var data;
        if (isGetData) {
            // 获取数据字符集集合
            data = GetData(dataUrl, dataParam);

            // 进行验证使用
            var data_r = JSON.parse(data);
            if (typeof (data_r) != undefined && data_r != null) {
                if (data_r.length > 0 || data_r.varList != undefined && data_r.varList.length > 0) {
                    // 绑定下拉数据
                    BindData(data);
                    myScroll.refresh();
                }
            } else {
                isGetData = false;
            }
        }
    }
    // 下拉刷新
    if (this.y > 40) {
        isGetData = true;
        currentPage = 0;
        dataParam.currentPage = currentPage;
        var data = GetData(dataUrl, dataParam);
        $("#list").empty();
        BindData(data);
        myScroll.refresh();
    }
}

// 初始化下拉刷新
function loaded() {

    myScroll = new IScroll('#wrapper', {
        probeType: 3,
        mouseWheel: true,//鼠标滑轮开启
        click: true,
    });

    myScroll.on('scroll', updatePosition);
    myScroll.on('scrollEnd', updatePosition);

    // 初始化加载数据
    var data = GetData(dataUrl, dataParam);
    var data_r = JSON.parse(data);
    BindData(data);
    myScroll.refresh();
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);