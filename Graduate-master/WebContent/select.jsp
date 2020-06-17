<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import = "com.bean.UserBean,com.starry.dao.UserDao,com.bean.ScheBean,com.starry.dao.BookDao" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
	<meta charset="UTF-8">
	<title></title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	    <link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
        <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
        <script src="static/js/bootstrap.min.js"></script>
        <script src="static/jQuery/jquery-3.1.1.min.js"></script>
            <script src="static/js/bootstrap-dropdown.min.js"></script>
              <script src="static/js/reader.js"></script>
              
             <script src="ajax-lib/ajaxutils.js"></script>
            <script src="static/js/readerUpdateInfo.js"></script>
             <script src="static/js/readerUpdatePwd.js"></script>

</head>



<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>


<body class="bootstrap-admin-with-small-navbar">
<!-- 判断是否已经登录 -->
<%
UserBean admin = new UserBean();
String aid = (String)session.getAttribute("aid");
UserDao admindao = new UserDao();
admin = admindao.get_AidInfo2(aid);

%>

        <!-- content -->
        <div class="col-md-10">
       <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">查询</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal" action="/Graduate/selectServlet" method="post">
                                <input type="hidden" name="tip" value="2">
                                    <div class="col-lg-8 form-group">
                                        <label class="col-lg-4 control-label" for="query_bname">医生名称</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="bookName" name="name" type="text" value="">
                                            <label class="control-label" for="query_bname" style="display: none;"></label>
                                        </div>
                                    </div>
                                    
                                  
                                    <div class="col-lg-4 form-group">

                                        <button type="submit" class="btn btn-primary" id="btn_query" >查询</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <div class="row">
                <div class="col-lg-12">

                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>挂号单</th>
                                <th>医生姓名</th>
                                <th>科室名称</th>
                                <th>剩余挂号数</th>
                                 <th>价格</th>
                                <th>开始时间</th>
                                <th>结束时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            
                      
                            <!---在此插入信息-->
                            <!---在此插入信息-->
                             <%
                             ArrayList<ScheBean> bookdata = new ArrayList<ScheBean>();
                             bookdata = (ArrayList<ScheBean>)request.getAttribute("data");
                           if(bookdata==null){
                        	   BookDao bookdao = new BookDao();
                        	   bookdata = (ArrayList<ScheBean>)bookdao.get_ListInfo();
                           }
	
  for (ScheBean bean : bookdata){
  %>                 

	                         	   	<td><%= bean.getsNumber() %></td>
	                                <td><%= bean.getDname() %></td>
	                                <td><%= bean.getPartName() %></td>
	                                <td><%= bean.getTotal() %></td>
	                                <td><%= bean.getPrice()  %></td>
	                                <td><%= bean.getsTime() %></td>   
	                                <td><%= bean.geteTime() %></td>
<td><button type="button" class="btn btn-info btn-xs" data-toggle="modal" onclick="borrowbook(<%= bean.getsNumber() %>)" >预约</button> 
<button type="button" class="btn btn-info btn-xs" data-toggle="modal" onclick="getInfo(<%= bean.getdNumber() %>)" >查看医生信息</button>  	</td>                                            
                                          
                          	  </tbody>
                       <%} %> 
                            
                        </table>
                        
                        
                           
                    </div>
                </div>
         
         <script type="text/javascript">
    function borrowbook(bid) {
    	con=confirm("是否预约?"); 
    	if(con==true){
    		location.href = "/Graduate/borrowServlet?tip=1&bid="+bid;
    	}
    }
    function getInfo(sid) {
		location.href = "/Graduate/InfoServlet?sid="+sid;
    }
    </script>
     
         <script type="text/javascript">
    function getinfo(did) {
    	location.href = "/Graduate/borrowServlet?did="+did;
    }
    </script>
        </div>

    
    
    

<!-------------------------------------------------------------->  
                 
                   <form class="form-horizontal" method="post" action="/books/AdminServlet">   <!--保证样式水平不混乱-->                  
                                     <!-- 模态框（Modal） -->
				<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									修改密码
								</h4>
							</div>
							<div class="modal-body">
							 
								<!--正文-->
								<input type="hidden" name="tip" value="1">
								<input type="hidden" name="url" value="select">
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">原密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" name="password" id="oldPwd"  placeholder="请输入原密码">
										<label class="control-label" for="oldPwd" style="display: none"></label>				
								</div>
							</div>	
							
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">新密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" name="password2" id="newPwd"  placeholder="请输入新密码">
										<label class="control-label" for="newPwd" style="display: none"></label>			
								</div>
							</div>	
							
								<!--正文-->
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type="submit" class="btn btn-primary" >
									修改
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>

				</form>	
                               
    
    




    <div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="infoModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" id="div_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>



</body>
</html>