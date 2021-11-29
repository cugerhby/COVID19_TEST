<%--
  Created by IntelliJ IDEA.
  User: 黄邦宇的电脑
  Date: 2021/8/30
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
      <script  src="js/echarts.min.js" ></script>
      <script src="js/jquery-3.6.0.js"></script>
      <script src="js/china.js"></script>

  </head>

  <style>
    body{
        margin-bottom: 0px;
        margin-left: 0px;
        margin-right: 0px;
        margin-top: 0px;

    }

    #top{
      width: 100%;
      height: 150px;
      padding-top: 30px;

     /* 外边距和内边距都要设置为0*/

      background-color: #082b74;
    }
    #chose_bar{
      width: 100%;
      height: 50px;
      background-color: #206db7;
    }
    table,th{

        margin: auto;
        padding-top: 5px;
        font-size: 18px;
        color: white;
        padding-left:20px;
        padding-right: 20px;
    }
    #container{
        background-color:white;
        width: 100%;
        height: 600px;
        padding-top: 5px;



    }
    #container-show-school{
        background-color: #474747;
        width: 97%;
        height: 55px;
        margin-top: 5px;
        margin: auto;
        border-radius: 20px;
        padding-top: 10px;

    }



  </style>


  <body>
  <div id="top">

      <%--display是显示方式 block是独占一行 inline是都占一行站不下了才会换下一行--%>
      <%-- 让图片向左浮动，就可以实现文字环绕图片的效果--%>
<div style="margin-left: 100px;margin-top: 20px">
      <img src="images/logo.png" style="float: left">
      <span style="color: white;font-size: 27px">
        CORONAVIRUS
        </span>
      <br>
      <span style="color: white;font-size: 27px;clear: left">
        RESOURCE CENTER
        </span>
</div>
  </div>
  <div id="chose_bar" style="text-align: center">
      <form>
    <table >
      <tr>
        <th>
          <select style="color: white;font-size: 18px;background-color:#206db7;border: 0px">
              <option>GLOBAL MAP</option>
              <option>Asian Map</option>
              <option>Afican Map</option>
              <option>U.S Map</option>
          </select>
        </th>
        <th>
          CHINA MAP
        </th>
        <th>
          DATA in Motion
        </th>
      </tr>
    </table>
      </form>
  </div>


<%--  div套嵌会引起显示的问题，如果外层的div没有设置padding，那么内层的margin会转移到外层上去，导致出问题--%>
  <div id="container">
      <div id="container-show-school">
          <div style="color: white;font-size: 19px;text-align: left;margin-top: 10px;margin-left: 20px">
              COVID-19 China Cases by County
          </div>
      </div>
      <div>
      <div id="charts" style="height: 400px;width: 40%;padding-left:450px;text-align: center"></div>
      </div>


     <script type="text/javascript">


          var myChart= echarts.init(document.getElementById('charts'));
          //citydata为疫情数据,先获取省份数据再处理一下
          //provinceData为获取到的数据，全局变量最后处理一下
          var provinceData=null;
          var citydata=null;

          /*onload函数是等页面都加载完成后再执行的，所以在写的时候要考虑*/
          $.ajax({
              url:"${pageContext.request.contextPath}/sendProvinceJson",
              dataType:"JSON",
              type: "POST",
              async:false,
              success:function (req){
                 /* $("#sbsb").html(JSON.stringify(req));*/
                  provinceData=req;
                 /*此处获取到了req的数据*/
              }
              /*req是后台返回给前端的值，要注意仔细的鉴别以下*/
          });

          //给citydata
              citydata=getProvinceData(provinceData);


          //类对象，添加到数组里面去
          function province(name,data){
              this.name=name;
              this.value=data;
          }
          province.prototype.toString=function (){
              return this.value+this.name;
          }

          function getProvinceData(pro){
              var res=[];
              var a=null;
             /* 用each来遍历获取到的数据中的值*/
              $.each(pro,function(i,n){
                  //此处要在后端中对发送到前端的省份名称进行一个剪切操作
                  res.push(new province(n['provinceName'],n['currentConfirmedCount']));
              });
              return res;
          }


          var citydata2=[
              {
                  name: '北京',
                  value: 212
              }, {
                  name: '天津',
                  value: 60
              }, {
                  name: '上海',
                  value: 208
              }, {
                  name: '重庆',
                  value: 337
              }, {
                  name: '河北',
                  value: 126
              }, {
                  name: '河南',
                  value: 675
              }, {
                  name: '云南',
                  value: 117
              }, {
                  name: '辽宁',
                  value: 74
              }, {
                  name: '黑龙江',
                  value: 155
              }, {
                  name: '湖南',
                  value: 593
              }, {
                  name: '安徽',
                  value: 480
              }, {
                  name: '山东',
                  value: 270
              }, {
                  name: '新疆',
                  value: 29
              }, {
                  name: '江苏',
                  value: 308
              }, {
                  name: '浙江',
                  value: 829
              }, {
                  name: '江西',
                  value: 476
              }, {
                  name: '湖北',
                  value: 13522
              }, {
                  name: '广西',
                  value: 139
              }, {
                  name: '甘肃',
                  value: 55
              }, {
                  name: '山西',
                  value: 74
              }, {
                  name: '内蒙古',
                  value: 34
              }, {
                  name: '陕西',
                  value: 142
              }, {
                  name: '吉林',
                  value: 42
              }, {
                  name: '福建',
                  value: 179
              }, {
                  name: '贵州',
                  value: 56
              }, {
                  name: '广东',
                  value: 797
              }, {
                  name: '青海',
                  value: 15
              }, {
                  name: '西藏',
                  value: 1
              }, {
                  name: '四川',
                  value: 282
              }, {
                  name: '宁夏',
                  value: 34
              }, {
                  name: '海南',
                  value: 79
              }, {
                  name: '台湾',
                  value: 10
              }, {
                  name: '香港',
                  value: 15
              }, {
                  name: '澳门',
                  value: 9
              }
          ];


          // 指定图表的配置项和数据
          option = {
              title: {
              },
              tooltip: {
                  trigger: 'item'
              },
              legend: {
                  orient: 'vertical',
                  left: 'left',
                  data: ['中国疫情图']
              },
              visualMap: {
                  type: 'piecewise',
                  pieces: [
                      {min: 1000, max: 1000000, label: '大于等于1000人', color: '#372a28'},
                      {min: 500, max: 999, label: '确诊500-999人', color: '#4e160f'},
                      {min: 100, max: 499, label: '确诊100-499人', color: '#974236'},
                      {min: 10, max: 99, label: '确诊10-99人', color: '#ee7263'},
                      {min: 1, max: 9, label: '确诊1-9人', color: '#f5bba7'},
                  ],
                  color: ['#E0022B', '#E09107', '#A3E00B']
              },
              toolbox: {
                  show: true,
                  orient: 'vertical',
                  left: 'right',
                  top: 'center',
                  feature: {
                      mark: {show: true},
                      dataView: {show: true, readOnly: false},
                      restore: {show: true},
                      saveAsImage: {show: true}
                  }
              },
              roamController: {
                  show: true,
                  left: 'right',
                  mapTypeControl: {
                      'china': true
                  }
              },
              series: [
                  {
                      name: '确诊数',
                      type: 'map',
                      mapType: 'china',
                      roam: false,
                      label: {
                          show: true,
                          color: 'rgb(249, 249, 249)'
                      },
                      data:citydata
                  }
              ]
          };

          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);
      </script>
  </div>
  <div name="down" style="background-color: #082b74;width: 100%;height: 100px">

  </div>





  </body>





</html>
