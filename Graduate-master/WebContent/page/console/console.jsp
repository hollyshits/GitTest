<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 2020/5/13
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>控制台</title>
    <link rel="stylesheet" href="../../assets/libs/layui/css/layui.css"/>
    <link rel="stylesheet" href="../../assets/module/admin.css?v=318"/>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        /** 统计快捷方式样式 */
        .console-link-block {
            font-size: 16px;
            padding: 20px 20px;
            border-radius: 4px;
            background-color: #40D4B0;
            color: #FFFFFF !important;
            box-shadow: 0 2px 3px rgba(0, 0, 0, .05);
            position: relative;
            overflow: hidden;
            display: block;
        }

        .console-link-block .console-link-block-num {
            font-size: 40px;
            margin-bottom: 5px;
            opacity: .9;
        }

        .console-link-block .console-link-block-text {
            opacity: .8;
        }

        .console-link-block .console-link-block-icon {
            position: absolute;
            top: 50%;
            right: 20px;
            width: 50px;
            height: 50px;
            font-size: 50px;
            line-height: 50px;
            margin-top: -25px;
            color: #FFFFFF;
            opacity: .8;
        }

        .console-link-block .console-link-block-band {
            color: #fff;
            width: 100px;
            font-size: 12px;
            padding: 2px 0 3px 0;
            background-color: #E32A16;
            line-height: inherit;
            text-align: center;
            position: absolute;
            top: 8px;
            right: -30px;
            transform-origin: center;
            transform: rotate(45deg) scale(.8);
            opacity: .95;
            z-index: 2;
        }

        /** //统计快捷方式样式 */

        /** 设置每个快捷块的颜色 */
        .layui-row > div:nth-child(2) .console-link-block {
            background-color: #55A5EA;
        }

        .layui-row > div:nth-child(3) .console-link-block {
            background-color: #9DAFFF;
        }

        .layui-row > div:nth-child(4) .console-link-block {
            background-color: #F591A2;
        }

        .layui-row > div:nth-child(5) .console-link-block {
            background-color: #FEAA4F;
        }

        .layui-row > div:last-child .console-link-block {
            background-color: #9BC539;
        }
    </style>
</head>
<body>
<!-- 正文开始 -->
<div class="layui-fluid ew-console-wrapper">
    <!-- 快捷方式 -->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md2 layui-col-sm4 layui-col-xs6">
            <div class="console-link-block">
                <div class="console-link-block-num">15</div>
                <div class="console-link-block-text">停诊记录</div>
                <i class="console-link-block-icon layui-icon layui-icon-survey"></i>
                <div class="console-link-block-band">待审批</div>
            </div>
        </div>
        <%--<div class="layui-col-md2 layui-col-sm4 layui-col-xs6">
            <div class="console-link-block">
                <div class="console-link-block-num">13</div>
                <div class="console-link-block-text">请假审批</div>
                <i class="console-link-block-icon layui-icon layui-icon-print"></i>
                <div class="console-link-block-band">待审批</div>
            </div>
        </div>--%>
        <div class="layui-col-md2 layui-col-sm4 layui-col-xs6">
            <div class="console-link-block">
                <div class="console-link-block-num">22</div>
                <div class="console-link-block-text">本周排班</div>
                <i class="console-link-block-icon layui-icon layui-icon-list"
                   style="font-size: 62px;padding-right: 5px;"></i>
                <div class="console-link-block-band">去查看</div>
            </div>
        </div>
        <%--<div class="layui-col-md2 layui-col-sm4 layui-col-xs6">
            <div class="console-link-block">
                <div class="console-link-block-num">18</div>
                <div class="console-link-block-text">研发月报</div>
                <i class="console-link-block-icon layui-icon layui-icon-date"></i>
                <div class="console-link-block-band">去查看</div>
            </div>
        </div>--%>
        <div class="layui-col-md2 layui-col-sm4 layui-col-xs6">
            <div class="console-link-block">
                <div class="console-link-block-num">11</div>
                <div class="console-link-block-text">拜访记录</div>
                <i class="console-link-block-icon layui-icon layui-icon-service"></i>
                <div class="console-link-block-band">去查看</div>
            </div>
        </div>
        <%--<div class="layui-col-md2 layui-col-sm4 layui-col-xs6">
            <div class="console-link-block">
                <div class="console-link-block-num">26</div>
                <div class="console-link-block-text">项目申报</div>
                <i class="console-link-block-icon layui-icon layui-icon-email"></i>
                <div class="console-link-block-band">待审批</div>
            </div>
        </div>--%>
    </div>
    <!-- 统计图表 -->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4 layui-col-sm6">
            <div class="layui-card" style="overflow: hidden;">
                <div class="layui-card-header">日统计</div>
                <div class="layui-card-body">
                    <div id="consoleChartsDay" style="height: 300px;"></div>
                    <div style="color: #10B4E8;font-size: 18px;position: absolute;bottom: 85px;left: 0;right:0;text-align: center;cursor: pointer;">
                        预约明细<i class="layui-icon layui-icon-right" style="font-size: 16px;"></i>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4 layui-col-sm6">
            <div class="layui-card" style="overflow: hidden;">
                <div class="layui-card-header">周统计</div>
                <div class="layui-card-body">
                    <div id="consoleChartsWeek" style="height: 300px;"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4 layui-col-sm6">
            <div class="layui-card" style="overflow: hidden;">
                <div class="layui-card-header">月统计</div>
                <div class="layui-card-body">
                    <div id="consoleChartsMonth" style="height: 300px;"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4 layui-col-sm6">
            <div class="layui-card" style="overflow: hidden;">
                <div class="layui-card-header">热门搜索</div>
                <div class="layui-card-body">
                    <div id="consoleChartsWord" style="height: 300px;"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md8 layui-col-sm12">
            <div class="layui-card" style="overflow: hidden;">
                <div class="layui-card-header">用户分布</div>
                <div class="layui-card-body">
                    <div class="layui-row">
                        <div class="layui-col-md8 layui-col-sm7">
                            <div id="consoleChartsMap" style="height: 300px;"></div>
                        </div>
                        <div class="layui-col-md4 layui-col-sm5">
                            <table class="layui-table" lay-skin="line" style="margin-top: 15px;">
                                <thead>
                                <tr>
                                    <td>排名</td>
                                    <td>地区</td>
                                    <td>人数</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>浙江</td>
                                    <td>62310</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>上海</td>
                                    <td>59190</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>广东</td>
                                    <td>55891</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>北京</td>
                                    <td>51919</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>山东</td>
                                    <td>39231</td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td>湖北</td>
                                    <td>37109</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- js部分 -->
<script type="text/javascript" src="../../assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="../../assets/js/common.js?v=318"></script>
<script src="../../assets/libs/echarts/echarts.min.js"></script>
<script src="../../assets/libs/echarts/china.js"></script>
<script src="../../assets/libs/echarts/echarts-wordcloud.min.js"></script>
<script>
    $(document).ready(function() {
        console.log(111);
        /** 渲染日统计图表 */
        $.ajax({
            url : "${pageContext.request.contextPath}/json2",//后台请求的数据，用的是PHP
            success : function(data) {
                console.log(data);  //如果请求成功，返回数据。
                var k=0
                for (i=0;i<data.length;i++){
                    var s=data[i].status;
                    console.log(s)
                    if(s==4){
                        k++;
                    }
                }
                console.log(data);
                console.log(k);
                var myCharts1 = echarts.init(document.getElementById('consoleChartsDay'));
                var options1 = {
                    title: {
                        text: '已就诊人数/预约人数', x: 'center', y: '32%',
                        textStyle: {fontSize: 18, color: '#262626', fontWeight: 'normal'},
                        subtextStyle: {fontSize: 56, color: '#10B4E8'}, itemGap: 20
                    },
                    color: ['#10B4E8', '#E0E0E0'],
                    tooltip: {trigger: 'item'},
                    legend: {
                        orient: 'vertical', right: '0px', top: '0px',
                        data: ['已就诊', '未就诊'], textStyle: {color: '#595959'}
                    },
                    series: [{name: '人数', type: 'pie', radius: ['75%', '80%'], label: {normal: {show: false}}}]
                };
                myCharts1.setOption(options1);
                // 赋值
                myCharts1.setOption({
                    title: {subtext: k+"/"+data.length}, series: [{data: [{name: '就诊人数', value: k}, {name: '预约人数', value: data.length}]}]
                });
            },
        })
        /** 渲染周统计图表 */
        $.ajax({
            url : "${pageContext.request.contextPath}/weekdata",//后台请求的数据，用的是PHP
            success : function(data) {
                console.log(data);  //如果请求成功，返回数据。
                var myCharts2 = echarts.init(document.getElementById('consoleChartsWeek'));
                var options2 = {
                    tooltip: {trigger: 'axis', axisPointer: {lineStyle: {color: '#E0E0E0'}}},
                    color: ['#10B4E8', '#FFA800'],
                    legend: {
                        orient: 'vertical', right: '0px', top: '0px',
                        data: ['已就诊', '未就诊'], textStyle: {color: '#595959'}
                    },
                    grid: {top: '75px', left: '35px', right: '55px', bottom: '40px'},
                    xAxis: {
                        name: '星期',
                        nameTextStyle: {color: '#595959'},
                        type: 'category',
                        data: ['前五天', '前四天', '前三天', '前两天', '前一天'],
                        axisLine: {lineStyle: {color: '#E0E0E0'}, symbol: ['none', 'arrow'], symbolOffset: [0, 10]},
                        axisLabel: {color: '#8c8c8c'},
                        axisTick: {alignWithLabel: true}
                    },
                    yAxis: {
                        name: '人数',
                        nameTextStyle: {color: '#595959'},
                        type: 'value',
                        boundaryGap: ['0', '20%'],
                        axisTick: {show: false},
                        axisLine: {lineStyle: {color: '#E0E0E0'}, symbol: ['none', 'arrow'], symbolOffset: [0, 10]},
                        axisLabel: {color: '#8c8c8c'},
                        splitLine: {show: false},
                        splitArea: {show: false},
                        minInterval: 1
                    },
                    series: [{
                        name: '已就诊', type: 'bar', stack: 'one', barMaxWidth: '30px',
                        label: {normal: {show: true, position: 'inside'}}
                    }, {
                        name: '未就诊', type: 'bar', stack: 'one', barMaxWidth: '30px',
                        label: {normal: {show: true, position: 'inside'}}
                    }]
                };
                myCharts2.setOption(options2);
                // 赋值
                myCharts2.setOption({series: [{data: [data[0], data[2], data[4], data[6], data[8]]}, {data: [data[1], data[3], data[5], data[7], data[9]]}]});
            },
        })

        $.ajax({
            url : "${pageContext.request.contextPath}/wordcloud",//后台请求的数据，用的是PHP
            dataType:"json",
            success : function(data) {
                console.log(data)
                var res=[];
                for (var key in data) {
                    /*console.log(key);*/
                    res.push({
                        value:data[key],
                        name:key
                    })
                   /* console.log(data[key]);*/
                }
                console.log(res)
                var myCharts5 = echarts.init(document.getElementById('consoleChartsWord'));
                var options5 = {
                    tooltip: {show: true},
                    series: [{
                        name: "搜索量",
                        type: 'wordCloud',
                        shape: 'diamond',
                        width: '100%',
                        height: '100%',
                        sizeRange: [12, 23],
                        gridSize: 6,
                        textStyle: {
                            normal: {
                                color: function () {
                                    return 'rgb(' + [
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160),
                                        Math.round(Math.random() * 160)
                                    ].join(',') + ')';
                                }
                            },
                            emphasis: {shadowBlur: 10, shadowColor: '#666'}
                        }, data: []
                    }]
                };
                myCharts5.setOption(options5);
                // 赋值
                myCharts5.setOption({
                    series: [{
                        data: res
                    }]
                });
            },
        })



    })


    layui.use(['layer'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;

        /** 渲染日统计图表 */
       /* var myCharts1 = echarts.init(document.getElementById('consoleChartsDay'));
        var options1 = {
            title: {
                text: '签到人数/应到人数', x: 'center', y: '32%',
                textStyle: {fontSize: 18, color: '#262626', fontWeight: 'normal'},
                subtextStyle: {fontSize: 56, color: '#10B4E8'}, itemGap: 20
            },
            color: ['#10B4E8', '#E0E0E0'],
            tooltip: {trigger: 'item'},
            legend: {
                orient: 'vertical', right: '0px', top: '0px',
                data: ['已签到', '未签到'], textStyle: {color: '#595959'}
            },
            series: [{name: '人数', type: 'pie', radius: ['75%', '80%'], label: {normal: {show: false}}}]
        };
        myCharts1.setOption(options1);
        // 赋值
        myCharts1.setOption({
            title: {subtext: '38/60'}, series: [{data: [{name: '已签到', value: 38}, {name: '未签到', value: 22}]}]
        });*/

        /** 渲染周统计图表 */
        /*var myCharts2 = echarts.init(document.getElementById('consoleChartsWeek'));
        var options2 = {
            tooltip: {trigger: 'axis', axisPointer: {lineStyle: {color: '#E0E0E0'}}},
            color: ['#10B4E8', '#FFA800'],
            legend: {
                orient: 'vertical', right: '0px', top: '0px',
                data: ['已签到', '未签到'], textStyle: {color: '#595959'}
            },
            grid: {top: '75px', left: '35px', right: '55px', bottom: '40px'},
            xAxis: {
                name: '星期',
                nameTextStyle: {color: '#595959'},
                type: 'category',
                data: ['周一', '周二', '周三', '周四', '周五'],
                axisLine: {lineStyle: {color: '#E0E0E0'}, symbol: ['none', 'arrow'], symbolOffset: [0, 10]},
                axisLabel: {color: '#8c8c8c'},
                axisTick: {alignWithLabel: true}
            },
            yAxis: {
                name: '人数',
                nameTextStyle: {color: '#595959'},
                type: 'value',
                boundaryGap: ['0', '20%'],
                axisTick: {show: false},
                axisLine: {lineStyle: {color: '#E0E0E0'}, symbol: ['none', 'arrow'], symbolOffset: [0, 10]},
                axisLabel: {color: '#8c8c8c'},
                splitLine: {show: false},
                splitArea: {show: false},
                minInterval: 1
            },
            series: [{
                name: '已签到', type: 'bar', stack: 'one', barMaxWidth: '30px',
                label: {normal: {show: true, position: 'inside'}}
            }, {
                name: '未签到', type: 'bar', stack: 'one', barMaxWidth: '30px',
                label: {normal: {show: true, position: 'inside'}}
            }]
        };
        myCharts2.setOption(options2);
        // 赋值
        myCharts2.setOption({series: [{data: [5, 9, 6, 3, 10]}, {data: [10, 6, 9, 12, 5]}]});*/

        /** 渲染月统计图表 */
        var myCharts3 = echarts.init(document.getElementById('consoleChartsMonth'));
        var options3 = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {lineStyle: {color: '#E0E0E0'}},
                formatter: '{b}号<br/><span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#10B4E8;"></span>{a0}: {c0}<br/><span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#FFA800;"></span>{a1}: {c1}'
            },
            color: ['#10B4E8', '#FFA800'],
            legend: {
                orient: 'vertical', right: '0px', top: '0px',
                data: ['已签到', '未签到'], textStyle: {color: '#595959'}
            },
            grid: {top: '75px', left: '35px', right: '55px', bottom: '40px'},
            xAxis: {
                name: '日期',
                nameTextStyle: {color: '#595959'},
                type: 'category',
                data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'],
                axisLine: {lineStyle: {color: '#E0E0E0'}, symbol: ['none', 'arrow'], symbolOffset: [0, 10]},
                axisLabel: {
                    color: '#8c8c8c', interval: function (index, value) {
                        return index === 0 || ((index + 1) % 5 === 0);
                    }
                },
                axisTick: {alignWithLabel: true}
            },
            yAxis: {
                name: '人数',
                nameTextStyle: {color: '#595959'},
                type: 'value',
                boundaryGap: ['0', '20%'],
                axisTick: {show: false},
                axisLine: {lineStyle: {color: '#E0E0E0'}, symbol: ['none', 'arrow'], symbolOffset: [0, 10]},
                axisLabel: {color: '#8c8c8c'},
                splitLine: {show: false},
                splitArea: {show: false},
                minInterval: 1
            },
            series: [
                {name: '已预约', type: 'line', smooth: false},
                {name: '已就诊', type: 'line', smooth: false}
            ]
        };
        myCharts3.setOption(options3);
        // 赋值
        myCharts3.setOption({
            series: [
                {data: [0, 4, 3, 13, 3, 4, 5, 6, 2, 1, 1, 1, 1, 9, 2, 0, 8, 6, 9, 2, 10, 0, 4, 0, 4, 6, 0, 1, 0, 1, 4]},
                {data: [0, 2, 2, 8, 3, 4, 1, 2, 2, 1, 0, 0, 1, 9, 1, 0, 5, 6, 8, 0, 9, 0, 4, 0, 3, 6, 0, 0, 0, 0, 1]}
            ]
        });

        /** 渲染地图图表 */
        var myCharts4 = echarts.init(document.getElementById('consoleChartsMap'));
        var options4 = {
            tooltip: {trigger: 'item'},
            dataRange: {
                min: 0, max: 6e4, text: ['高', '低'], color: ['#2395FF', '#f2f2f2'], itemHeight: 60, itemWidth: 12
            }, series: [{
                name: '用户数量', type: 'map', mapType: "china",
                itemStyle: {normal: {label: {show: true, color: '#262626'}, borderColor: '#dddddd'}},
                emphasis: {label: {show: true, color: '#fff'}, itemStyle: {areaColor: '#FACF20'}},
                top: '0px', left: '15px', bottom: '0px'
            }]
        };
        myCharts4.setOption(options4);
        // 赋值
        myCharts4.setOption({
            series: [{
                data: [
                    {name: "西藏", value: 60},
                    {name: "青海", value: 167},
                    {name: "宁夏", value: 210},
                    {name: "海南", value: 252},
                    {name: "甘肃", value: 502},
                    {name: "贵州", value: 570},
                    {name: "新疆", value: 661},
                    {name: "云南", value: 88},
                    {name: "重庆", value: 100},
                    {name: "吉林", value: 505},
                    {name: "山西", value: 212},
                    {name: "天津", value: 913},
                    {name: "江西", value: 101},
                    {name: "广西", value: 617},
                    {name: "陕西", value: 925},
                    {name: "黑龙江", value: 512},
                    {name: "内蒙古", value: 143},
                    {name: "安徽", value: 9530},
                    {name: "北京", value: 519},
                    {name: "福建", value: 376},
                    {name: "上海", value: 591},
                    {name: "湖北", value: 371},
                    {name: "湖南", value: 896},
                    {name: "四川", value: 310},
                    {name: "辽宁", value: 722},
                    {name: "河北", value: 345},
                    {name: "河南", value: 969},
                    {name: "浙江", value: 623},
                    {name: "山东", value: 392},
                    {name: "江苏", value: 359},
                    {name: "广东", value: 55}
                ]
            }]
        });

        /** 渲染词云图表 */
       /* var myCharts5 = echarts.init(document.getElementById('consoleChartsWord'));
        var options5 = {
            tooltip: {show: true},
            series: [{
                name: "搜索量",
                type: 'wordCloud',
                shape: 'diamond',
                width: '100%',
                height: '100%',
                sizeRange: [12, 23],
                gridSize: 6,
                textStyle: {
                    normal: {
                        color: function () {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        }
                    },
                    emphasis: {shadowBlur: 10, shadowColor: '#666'}
                }, data: []
            }]
        };
        myCharts5.setOption(options5);
        // 赋值
        myCharts5.setOption({
            series: [{
                data: [
                    {name: "软妹子", value: 23},
                    {name: "汪星人", value: 23},
                    {name: "长腿欧巴", value: 23},
                    {name: "萝莉", value: 22},
                    {name: "辣~", value: 22},
                    {name: "K歌", value: 22},
                    {name: "大长腿", value: 21},
                    {name: "川妹子", value: 21},
                    {name: "女神", value: 21},
                    {name: "米粉", value: 20},
                    {name: "专注设计", value: 20},
                    {name: "逛街", value: 20},
                    {name: "黑长直", value: 20},
                    {name: "海纳百川", value: 19},
                    {name: "萌萌哒", value: 19},
                    {name: "坚持", value: 19},
                    {name: "话唠", value: 19},
                    {name: "果粉", value: 18},
                    {name: "喵星人", value: 18},
                    {name: "花粉", value: 18},
                    {name: "衬衫控", value: 18},
                    {name: "宅男", value: 17},
                    {name: "小清新", value: 17},
                    {name: "眼镜男", value: 17},
                    {name: "琼瑶", value: 17},
                    {name: "穷游党", value: 16},
                    {name: "铲屎官", value: 16},
                    {name: "正太", value: 16},
                    {name: "中二病", value: 16},
                    {name: "夜猫子", value: 15},
                    {name: "逗比", value: 15},
                    {name: "腹黑", value: 15},
                    {name: "吃鸡", value: 15},
                    {name: "为了联盟", value: 14},
                    {name: "背包客", value: 14},
                    {name: "民谣", value: 14},
                    {name: "为了部落", value: 14},
                    {name: "懒癌患者", value: 13},
                    {name: "追剧", value: 13},
                    {name: "IT民工", value: 13},
                    {name: "CNB成员", value: 13},
                    {name: "选择困难", value: 12},
                    {name: "锤粉", value: 12},
                    {name: "欧皇", value: 12},
                    {name: "仙气十足", value: 12}
                ]
            }]
        });*/

        /** 窗口大小改变事件 */
        window.onresize = function () {
            myCharts1.resize();
            myCharts2.resize();
            myCharts3.resize();
            myCharts4.resize();
            myCharts5.resize();
        };

    });
</script>
</body>
</html>
