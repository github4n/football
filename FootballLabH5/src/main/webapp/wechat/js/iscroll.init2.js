var myScroll1;
var showCount1 = 9;// 显示的条数
var currentPage1 = 0;// 当前页数-1
var dataUrl1 = "";// 请求数据使用的url
var dataParam1;// 传递的参数，json
var isGetData1 = true;

// 加载数据
function updatePosition1() {
    // 上拉加载数据
    if (this.y < (this.maxScrollY - 40)) {
        currentPage1++;
        dataParam1.currentPage = currentPage1;
        var data;
        if (isGetData1) {
            // 获取数据字符集集合
            data = GetData(dataUrl1, dataParam1);

            // 进行验证使用
            var data_r = JSON.parse(data);
            if (typeof (data_r) != undefined && data_r != null && data_r.length > 0) {
                // 绑定下拉数据
                BindData1(data);
                myScroll1.refresh();
            } else {
                isGetData1 = false;
            }
        }
    }
    // 下拉刷新
    if (this.y > 40) {
        isGetData1 = true;
        currentPage1 = 0;
        dataParam1.currentPage = currentPage1;
        var data = GetData(dataUrl1, dataParam1);
        $("#list1").empty();
        BindData1(data);
        myScroll1.refresh();
    }
}

// 初始化下拉刷新
function loaded1() {

    myScroll1 = new IScroll('#wrapper1', {
        probeType: 3,
        mouseWheel: true,//鼠标滑轮开启
        click: true,
    });

    myScroll1.on('scroll', updatePosition);
    myScroll1.on('scrollEnd', updatePosition);

    // 初始化加载数据
    var data = GetData(dataUrl1, dataParam1);
    var data_r = JSON.parse(data);
    BindData1(data);
    myScroll1.refresh();
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);