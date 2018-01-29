<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Read Only by HTML5 UP</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="<%=request.getContextPath() %>/common/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/common/js/jquery.scrollzer.min.js"></script>
		<script src="<%=request.getContextPath() %>/common/js/jquery.scrolly.min.js"></script>
		<script src="<%=request.getContextPath() %>/common/js/skel.min.js"></script>
		<script src="<%=request.getContextPath() %>/common/js/skel-layers.min.js"></script>
		<script src="<%=request.getContextPath() %>/common/js/init.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/css/skel.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/css/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/common/css/style-xlarge.css" />
		
</head>
<body>
<div id="wrapper">

			<!-- Header -->
				<section id="header" class="skel-layers-fixed">
					<header>
						<span class="image avatar"><img src="<%=request.getContextPath() %>/common/style/images/avatar.jpg" alt="" /></span>
						<h1 id="logo"><a href="#">liyanippon</a></h1>
						<p>I got reprogrammed by a rogue AI<br />
						and now I'm totally cray</p>
					</header>
					<nav id="nav">
						<ul>
							<li><a href="#one" class="active">关于</a></li>
							<li><a href="#two">想我所想，做我所做</a></li>
							<li><a href="#three">点滴成就</a></li>
							<li><a href="#four">联系我</a></li>
						</ul>
					</nav>
					<footer>
						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
							<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
							<li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
						</ul>
					</footer>
				</section>

			<!-- Main -->
				<div id="main">

					<!-- One -->
						<section id="one">
							<div class="container">
								<header class="major">
									<h2>懂 你</h2>
									<p>这是一个小小的 网站<br />
									在这个网站 中蕴含着无限的 未来 <a href="http://www.cssmoban.com/tags.asp?page=7&n=html5">感谢 模板之家</a>.</p>
								</header>
								<p>Faucibus sed lobortis aliquam lorem blandit. Lorem eu nunc metus col. Commodo id in arcu ante lorem ipsum sed accumsan erat praesent faucibus commodo ac mi lacus. Adipiscing mi ac commodo. Vis aliquet tortor ultricies non ante erat nunc integer eu ante ornare amet commetus vestibulum blandit integer in curae ac faucibus integer non. Adipiscing cubilia elementum.</p>
							</div>
						</section>
						
					<!-- Two -->
						<section id="two">
							<div class="container">
								<h3>想做就做</h3>
								<p>时间是宝贵的， 稍不留意就已是昨日黄花， 在这有限的时间里 不妨做做下面的工作</p>
								<ul class="feature-icons">
									<li class="fa-code">写代码</li>
									<li class="fa-cubes">云服务</li>
									<li class="fa-book">读书与工作</li>
									<li class="fa-coffee">休闲时刻</li>
									<li class="fa-bolt">闪光 点</li>
									<li class="fa-users">技术分享</li>
								</ul>
							</div>
						</section>
                        
                        <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>
						
					<!-- Three -->
						<section id="three">
							<div class="container">
								<h3>点滴成就</h3>
								<p>Integer eu ante ornare amet commetus vestibulum blandit integer in curae ac faucibus integer non. Adipiscing cubilia elementum integer. Integer eu ante ornare amet commetus.</p>
								<div class="features">
									<article>
										<a href="#" class="image"><img src="<%=request.getContextPath() %>/common/style/images/pic01.jpg" alt="" /></a>
										<div class="inner">
											<h4>Possibly broke spacetime</h4>
											<p>Integer eu ante ornare amet commetus vestibulum blandit integer in curae ac faucibus integer adipiscing ornare amet.</p>
										</div>
									</article>
									<article>
										<a href="#" class="image"><img src="<%=request.getContextPath() %>/common/style/images/pic02.jpg" alt="" /></a>
										<div class="inner">
											<h4>Terraformed a small moon</h4>
											<p>Integer eu ante ornare amet commetus vestibulum blandit integer in curae ac faucibus integer adipiscing ornare amet.</p>
										</div>
									</article>
									<article>
										<a href="#" class="image"><img src="<%=request.getContextPath() %>/common/style/images/pic03.jpg" alt="" /></a>
										<div class="inner">
											<h4>Snapped dark matter in the wild</h4>
											<p>Integer eu ante ornare amet commetus vestibulum blandit integer in curae ac faucibus integer adipiscing ornare amet.</p>
										</div>
									</article>
								</div>
							</div>
						</section>
						
					<!-- Four -->
						<section id="four">
							<div class="container">
								<h3>联系 我</h3>
								<p>Integer eu ante ornare amet commetus vestibulum blandit integer in curae ac faucibus integer non. Adipiscing cubilia elementum integer. Integer eu ante ornare amet commetus.</p>
								<form method="post" action="#">
									<div class="row uniform">
										<div class="6u 12u(3)"><input type="text" name="name" id="name" placeholder="名字" /></div>
										<div class="6u 12u(3)"><input type="email" name="email" id="email" placeholder="邮箱" /></div>
									</div>
									<div class="row uniform">
										<div class="12u"><input type="text" name="subject" id="subject" placeholder="主题" /></div>
									</div>
									<div class="row uniform">
										<div class="12u"><textarea name="message" id="message" placeholder="消息" rows="6"></textarea></div>
									</div>
									<div class="row uniform">
										<div class="12u">
											<ul class="actions">
												<li><input type="submit" class="special" value="发 消息" /></li>
												<li><input type="reset" value="重置 内容" /></li>
											</ul>
										</div>
									</div>
								</form>
							</div>
						</section>
				
					<!-- Five -->
					
						<section id="five">
							<div class="container">
								<h3>Elements</h3>

								<section>
									<h4>Text</h4>
									<p>This is <b>bold</b> and this is <strong>strong</strong>. This is <i>italic</i> and this is <em>emphasized</em>.
									This is <sup>superscript</sup> text and this is <sub>subscript</sub> text.
									This is <u>underlined</u> and this is code: <code>for (;;) { ... }</code>. Finally, <a href="#">this is a link</a>.</p>
									<hr />
									<header>
										<h4>Heading with a Subtitle</h4>
										<p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
									</header>
									<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
									<header>
										<h5>Heading with a Subtitle</h5>
										<p>Lorem ipsum dolor sit amet nullam id egestas urna aliquam</p>
									</header>
									<p>Nunc lacinia ante nunc ac lobortis. Interdum adipiscing gravida odio porttitor sem non mi integer non faucibus ornare mi ut ante amet placerat aliquet. Volutpat eu sed ante lacinia sapien lorem accumsan varius montes viverra nibh in adipiscing blandit tempus accumsan.</p>
									<hr />
									<h2>Heading Level 2</h2>
									<h3>Heading Level 3</h3>
									<h4>Heading Level 4</h4>
									<h5>Heading Level 5</h5>
									<h6>Heading Level 6</h6>
									<hr />
									<h5>Blockquote</h5>
									<blockquote>Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan faucibus. Vestibulum ante ipsum primis in faucibus lorem ipsum dolor sit amet nullam adipiscing eu felis.</blockquote>
									<h5>Preformatted</h5>
									<pre><code>i = 0;

while (!deck.isInOrder()) {
    print 'Iteration ' + i;
    deck.shuffle();
    i++;
}

print 'It took ' + i + ' iterations to sort the deck.';</code></pre>
								</section>

								<section>
									<h4>Lists</h4>
									<div class="row">
										<div class="6u 12u(3)">
											<h5>Unordered</h5>
											<ul>
												<li>Dolor pulvinar etiam magna etiam.</li>
												<li>Sagittis adipiscing lorem eleifend.</li>
												<li>Felis enim feugiat dolore viverra.</li>
											</ul>
											<h5>Alternate</h5>
											<ul class="alt">
												<li>Dolor pulvinar etiam magna etiam.</li>
												<li>Sagittis adipiscing lorem eleifend.</li>
												<li>Felis enim feugiat dolore viverra.</li>
											</ul>
										</div>
										<div class="6u 12u(3)">
											<h5>Ordered</h5>
											<ol>
												<li>Dolor pulvinar etiam magna etiam.</li>
												<li>Etiam vel felis at lorem sed viverra.</li>
												<li>Felis enim feugiat dolore viverra.</li>
												<li>Dolor pulvinar etiam magna etiam.</li>
												<li>Etiam vel felis at lorem sed viverra.</li>
												<li>Felis enim feugiat dolore viverra.</li>
											</ol>
											<h5>Icons</h5>
											<ul class="icons">
												<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
												<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
												<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
												<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
												<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
												<li><a href="#" class="icon fa-tumblr"><span class="label">Tumblr</span></a></li>
											</ul>
										</div>
									</div>
									<h5>Actions</h5>
									<ul class="actions">
										<li><a href="#" class="button special">Default</a></li>
										<li><a href="#" class="button">Default</a></li>
										<li><a href="#" class="button alt">Default</a></li>
									</ul>
									<ul class="actions small">
										<li><a href="#" class="button special small">Small</a></li>
										<li><a href="#" class="button small">Small</a></li>
										<li><a href="#" class="button alt small">Small</a></li>
									</ul>
									<div class="row">
										<div class="3u 6u(2) 12u$(3)">
											<ul class="actions vertical">
												<li><a href="#" class="button special">Default</a></li>
												<li><a href="#" class="button">Default</a></li>
												<li><a href="#" class="button alt">Default</a></li>
											</ul>
										</div>
										<div class="3u 6u$(2) 12u$(3)">
											<ul class="actions vertical small">
												<li><a href="#" class="button special small">Small</a></li>
												<li><a href="#" class="button small">Small</a></li>
												<li><a href="#" class="button alt small">Small</a></li>
											</ul>
										</div>
										<div class="3u 6u(2) 12u$(3)">
											<ul class="actions vertical">
												<li><a href="#" class="button special fit">Default</a></li>
												<li><a href="#" class="button fit">Default</a></li>
												<li><a href="#" class="button alt fit">Default</a></li>
											</ul>
										</div>
										<div class="3u 6u$(2) 12u$(3)">
											<ul class="actions vertical small">
												<li><a href="#" class="button special small fit">Small</a></li>
												<li><a href="#" class="button small fit">Small</a></li>
												<li><a href="#" class="button alt small fit">Small</a></li>
											</ul>
										</div>
									</div>
								</section>

								<section>
									<h4>Table</h4>
									<h5>Default</h5>
									<div class="table-wrapper">
										<table>
											<thead>
												<tr>
													<th>Name</th>
													<th>Description</th>
													<th>Price</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Item One</td>
													<td>Ante turpis integer aliquet porttitor.</td>
													<td>29.99</td>
												</tr>
												<tr>
													<td>Item Two</td>
													<td>Vis ac commodo adipiscing arcu aliquet.</td>
													<td>19.99</td>
												</tr>
												<tr>
													<td>Item Three</td>
													<td> Morbi faucibus arcu accumsan lorem.</td>
													<td>29.99</td>
												</tr>
												<tr>
													<td>Item Four</td>
													<td>Vitae integer tempus condimentum.</td>
													<td>19.99</td>
												</tr>
												<tr>
													<td>Item Five</td>
													<td>Ante turpis integer aliquet porttitor.</td>
													<td>29.99</td>
												</tr>
											</tbody>
											<tfoot>
												<tr>
													<td colspan="2"></td>
													<td>100.00</td>
												</tr>
											</tfoot>
										</table>
									</div>
									
									<h5>Alternate</h5>
									<div class="table-wrapper">
										<table class="alt">
											<thead>
												<tr>
													<th>Name</th>
													<th>Description</th>
													<th>Price</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>Item One</td>
													<td>Ante turpis integer aliquet porttitor.</td>
													<td>29.99</td>
												</tr>
												<tr>
													<td>Item Two</td>
													<td>Vis ac commodo adipiscing arcu aliquet.</td>
													<td>19.99</td>
												</tr>
												<tr>
													<td>Item Three</td>
													<td> Morbi faucibus arcu accumsan lorem.</td>
													<td>29.99</td>
												</tr>
												<tr>
													<td>Item Four</td>
													<td>Vitae integer tempus condimentum.</td>
													<td>19.99</td>
												</tr>
												<tr>
													<td>Item Five</td>
													<td>Ante turpis integer aliquet porttitor.</td>
													<td>29.99</td>
												</tr>
											</tbody>
											<tfoot>
												<tr>
													<td colspan="2"></td>
													<td>100.00</td>
												</tr>
											</tfoot>
										</table>
									</div>
								</section>

								<section>
									<h4>Buttons</h4>
									<ul class="actions">
										<li><a href="#" class="button special">Special</a></li>
										<li><a href="#" class="button">Default</a></li>
										<li><a href="#" class="button alt">Alternate</a></li>
									</ul>
									<ul class="actions">
										<li><a href="#" class="button special big">Big</a></li>
										<li><a href="#" class="button">Default</a></li>
										<li><a href="#" class="button alt small">Small</a></li>
									</ul>
									<ul class="actions fit">
										<li><a href="#" class="button special fit">Fit</a></li>
										<li><a href="#" class="button fit">Fit</a></li>
										<li><a href="#" class="button alt fit">Fit</a></li>
									</ul>
									<ul class="actions fit small">
										<li><a href="#" class="button special fit small">Fit + Small</a></li>
										<li><a href="#" class="button fit small">Fit + Small</a></li>
										<li><a href="#" class="button alt fit small">Fit + Small</a></li>
									</ul>
									<ul class="actions">
										<li><a href="#" class="button special icon fa-download">Icon</a></li>
										<li><a href="#" class="button icon fa-download">Icon</a></li>
										<li><a href="#" class="button alt icon fa-check">Icon</a></li>
									</ul>
									<ul class="actions">
										<li><span class="button special disabled">Special</span></li>
										<li><span class="button disabled">Default</span></li>
										<li><span class="button alt disabled">Alternate</span></li>
									</ul>
								</section>

								<section>
									<h4>Form</h4>
									<form method="post" action="#">
										<div class="row uniform">
											<div class="6u 12u(3)">
												<input type="text" name="demo-name" id="demo-name" value="" placeholder="Name" />
											</div>
											<div class="6u 12u(3)">
												<input type="email" name="demo-email" id="demo-email" value="" placeholder="Email" />
											</div>
										</div>
										<div class="row uniform">
											<div class="12u">
												<div class="select-wrapper">
													<select name="demo-category" id="demo-category">
														<option value="">- Category -</option>
														<option value="1">Manufacturing</option>
														<option value="1">Shipping</option>
														<option value="1">Administration</option>
														<option value="1">Human Resources</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row uniform">
											<div class="4u 12u(2)">
												<input type="radio" id="demo-priority-low" name="demo-priority" checked>
												<label for="demo-priority-low">Low Priority</label>
											</div>
											<div class="4u 12u(2)">
												<input type="radio" id="demo-priority-normal" name="demo-priority">
												<label for="demo-priority-normal">Normal Priority</label>
											</div>
											<div class="4u 12u(2)">
												<input type="radio" id="demo-priority-high" name="demo-priority">
												<label for="demo-priority-high">High Priority</label>
											</div>
										</div>
										<div class="row uniform">
											<div class="6u 12u(2)">
												<input type="checkbox" id="demo-copy" name="demo-copy">
												<label for="demo-copy">Email me a copy of this message</label>
											</div>
											<div class="6u 12u(2)">
												<input type="checkbox" id="demo-human" name="demo-human" checked>
												<label for="demo-human">I am a human and not a robot</label>
											</div>
										</div>
										<div class="row uniform">
											<div class="12u">
												<textarea name="demo-message" id="demo-message" placeholder="Enter your message" rows="6"></textarea>
											</div>
										</div>
										<div class="row uniform">
											<div class="12u">
												<ul class="actions">
													<li><input type="submit" value="Send Message" /></li>
													<li><input type="reset" value="Reset" class="alt" /></li>
												</ul>
											</div>
										</div>
									</form>
								</section>

								<section>
									<h4>Image</h4>
									<h5>Fit</h5>
									<span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/banner.jpg" alt="" /></span>
									<div class="box alt">
										<div class="row 50% uniform">
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic01.jpg" alt="" /></span></div>
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic02.jpg" alt="" /></span></div>
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic03.jpg" alt="" /></span></div>
										</div>
										<div class="row 50% uniform">
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic02.jpg" alt="" /></span></div>
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic03.jpg" alt="" /></span></div>
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic01.jpg" alt="" /></span></div>
										</div>
										<div class="row 50% uniform">
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic03.jpg" alt="" /></span></div>
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic01.jpg" alt="" /></span></div>
											<div class="4u"><span class="image fit"><img src="<%=request.getContextPath() %>/common/style/images/pic02.jpg" alt="" /></span></div>
										</div>
									</div>
									<h5>双语 &amp; 阅读</h5>
									<p><span class="image left"><img src="<%=request.getContextPath() %>/common/style/images/avatar.jpg" alt="" /></span>・雪    日本の「四季」の景観を代表する「雪月花」の一つ。多くの詩歌や俳句の題材になっている。雪は六角形の結晶が多いので「六つの花」ともいう。細かい雪が風に乗ってちらちら舞う様子を「風
花が舞う」という。雪は、形や状況によって、様々な呼び方がある。白雪、粉雪、牡丹雪、ざらめ雪、 細雪、小雪、淡雪、根雪、なごり雪、風雪、吹雪など。「降る雪や 明治は遠く なりにけり」(中村草 田男 <br>・氷柱 家の軒や木から落ちる水のしずくが、凍って棒のように垂れ下がったもの。
「御み仏の 御お鼻の先へ つららかな」(小林 一茶)<br>・春浅し暦の上では春だが、冬の気配が残り、まだ春は浅い、と感じる。「早春賦」という 唱歌に、「春は名のみの 風の寒さや、、、♪♪」とある。</p>
									<p><span class="image right"><img src="<%=request.getContextPath() %>/common/style/images/avatar.jpg" alt="" /></span>Fringilla nisl. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent. Donec accumsan interdum nisi, quis tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent tincidunt felis sagittis eget. tempus euismod. Vestibulum ante ipsum primis in faucibus vestibulum. Blandit adipiscing eu felis iaculis volutpat ac adipiscing accumsan eu faucibus. Integer ac pellentesque praesent.</p>
								</section>

							</div>
						</section>
					
				
				</div>

			<!-- Footer -->
				<section id="footer">
					<div class="container">
						<ul class="copyright">
							<li>&copy; Untitled. All rights reserved.</li><li>More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></li>
						</ul>
					</div>
				</section>
			
		</div>
</body>
</html>