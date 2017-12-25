<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/index.js"></script>
<body>
	<section class="widget small">
			<header> 
				<span class="icon">&#128196;</span>
				<hgroup>
					<h1>新闻内容</h1>
					<h2>news content</h2>
				</hgroup>
			</header>
			
		    <div class="content cycle">
			<div id="flot-example-2" class="graph-area"  >
		</div>
   
		</div>
			
			
		</section>
	<section class="widget small">
			<header> 
				<span class="icon">&#128196;</span>
				<hgroup>
					<h1>小区新闻</h1>
					<h2>news</h2>
				</hgroup>
				<aside>
					
				</aside>
				</header>
				<table id="myTable" border="0" width="100" class="myTable">
				<thead>
					<tr>
						<th style="width: 10%;">序号</th>
						<th style="width: 50%;">标题</th>
						<th style="width: 40%;">时间</th>
					</tr>
				</thead>
					<tbody id="tbody">
					</tbody>
				</table>
		</section>
</body>
</html>