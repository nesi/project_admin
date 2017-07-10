<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<!-- saved from url=(0056)https://projects.nesi.org.nz/nesi-config/projects/active -->
<!--[if IE 6 ]> <html class="ie6"> <![endif]-->
<!--[if IE 7 ]> <html class="ie7"> <![endif]-->
<!--[if IE 8 ]> <html class="ie8"> <![endif]-->
<!--[if IE 9 ]> <html class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<HTML lang="en">
<!--<![endif]-->
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">

<META charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=0.27, maximum-scale=1.0"> -->
<LINK href="/sites/default/themes/nesi_bootstrap/favicon.ico"
	rel="shortcut icon" type="image/x-icon">
<LINK title="News &amp; Events"
	href="https://projects.nesi.org.nz/news-events/feed" rel="alternate"
	type="application/rss+xml">
<META name="GENERATOR" content="MSHTML 11.00.10570.1001">
<LINK href="https://projects.nesi.org.nz/nesi-config/projects/active"
	rel="canonical">
<LINK href="https://projects.nesi.org.nz/nesi-config/projects/active"
	rel="shortlink">
<META content="New Zealand eScience Infrastructure"
	property="og:site_name">
<META content="article" property="og:type">
<META content="https://projects.nesi.org.nz/nesi-config/projects/active"
	property="og:url">
<META content="NeSI Admin" property="og:title">
<META name="twitter:card" content="summary">
<META name="twitter:url"
	content="https://projects.nesi.org.nz/nesi-config/projects/active">
<META name="twitter:title" content="NeSI Admin">
<TITLE>NeSI Admin | New Zealand eScience Infrastructure</TITLE>
<LINK href="css/css_lQaZfjVpwP_oGNqdtWCSpJT1EMqXdMiU84ekLLxQnc4.css"
	rel="stylesheet" type="text/css" media="all">
<LINK href="css/css_0_z5rysGdMKpWy6UM66toLYGz1HiZCm2vLu7lF4L0F4.css"
	rel="stylesheet" type="text/css" media="all">
<LINK href="css/css_y7x-E9HU1xpIegvHoNXPU22DGIObba-YNi7929-l8do.css"
	rel="stylesheet" type="text/css" media="all">
<LINK href="css/css_rPSZpucGfX4oJF-mdbMmwMjs_W_1XyqDwp6AhF2sy10.css"
	rel="stylesheet" type="text/css" media="all">
<SCRIPT
	src="https://projects.nesi.org.nz/sites/default/files/js/js_mj8--jUKjR2GqRpB2rxGEcuQTBbR480BEkLzP0vXCws.js"></SCRIPT>

<SCRIPT
	src="https://projects.nesi.org.nz/sites/default/files/js/js_hd8DPwd7VUdM3UGN8K_Fe7KwcpMBdZfvxoP5Lm1i_eE.js"></SCRIPT>

<SCRIPT
	src="https://projects.nesi.org.nz/sites/default/files/js/js_d6y1pKLAQrJcRYN2ozYC1JaYpaSGWhaxEcTqvMJne98.js"></SCRIPT>

<SCRIPT></SCRIPT>

<SCRIPT
	src="https://projects.nesi.org.nz/sites/default/files/js/js_rv_BKYv7yieH0IgHddhWHDC-bWGan8yiJbusyOpr0mw.js"></SCRIPT>

<SCRIPT
	src="https://projects.nesi.org.nz/sites/default/files/js/js_qdAFA9VZ0NsN7RnQTa17Ed-NaioF5l0nZWWwYfv3fqg.js"></SCRIPT>

<!-- HTML5 element support for IE6-8 -->
<!--[if lt IE 9]>
    <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
<!-- Le fav and touch icons -->
<LINK
	href="/sites/default/themes/nesi_bootstrap/assets/ico/apple-touch-icon-144-precomposed.png"
	rel="apple-touch-icon-precomposed" sizes="144x144">
<LINK
	href="/sites/default/themes/nesi_bootstrap/assets/ico/apple-touch-icon-114-precomposed.png"
	rel="apple-touch-icon-precomposed" sizes="114x114">
<LINK
	href="/sites/default/themes/nesi_bootstrap/assets/ico/apple-touch-icon-72-precomposed.png"
	rel="apple-touch-icon-precomposed" sizes="72x72">
<LINK
	href="/sites/default/themes/nesi_bootstrap/assets/ico/apple-touch-icon-57-precomposed.png"
	rel="apple-touch-icon-precomposed">
<LINK href="css/css.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY
	class="html not-front logged-in no-sidebars page-nesi-config page-nesi-config-projects page-nesi-config-projects-active nesi-config projects active">
	<DIV class="navbar">
		<DIV class="navbar-inner">
			<DIV class="container">
				<!-- .btn-navbar is used as the toggle for collapsed navbar content -->
				<A class="btn btn-navbar" data-target=".nav-collapse"
					data-toggle="collapse"><SPAN class="icon-bar"></SPAN><SPAN
					class="icon-bar"></SPAN><SPAN class="icon-bar"></SPAN></A> <A
					class="brand" href="https://projects.nesi.org.nz/"><SPAN>NeSI
						– New Zealand eScience Infrastructure</SPAN></A>
				<DIV class="nav-collapse collapse">
					<NAV id="pnav" role="navigation">
						<SECTION class="block block-nesi-login-modal"
							id="block-nesi-login-modal-nesi-login-modal-menu">
							<UL class="nav pull-right">
								<LI><A class="dropdown-toggle"
									href="https://projects.nesi.org.nz/nesi-config/projects/active#"
									data-target="#" data-toggle="dropdown">${requestScope.cn}</A>
									<UL class="dropdown-menu pull-right"
										id="nesi-user-profile-dropdown">
										<LI id="nesi-user-picture">
											<DIV class="nesi-user-picture">
												<DIV class="pull-left">
													<DIV class="user-picture">
														<A title="View user profile."
															href="https://projects.nesi.org.nz/users/1998"><IMG
															title="Joe Bloggs's picture" alt="Joe Bloggs's picture"
															src="img/nesi_avatar.png" typeof="foaf:Image"></A>
													</DIV>
												</DIV>
												<H2>${requestScope.cn}</H2>
												<H3>${requestScope.o}</H3>
												<P></P>
												<P>
													<EM></EM>
												</P>
											</DIV>
										</LI>
										<LI><A href="/nesi-config">Administration Dashboard</A></LI>
										<LI class="first last leaf menu-1716"><A href="/user/dashboard">Proposals
												and Projects</A></LI>
										<LI id="nesi-base-actions">
											<UL class="nav nav-pills nesi-base-actions">
												<LI class="pull-left"><A
													href="https://projects.nesi.org.nz/user">Profile</A></LI>
												<LI class="pull-right"><A
													href="/user/logout?return=https://projects.test.nesi.org.nz/project_admin/case_studies">Log
														out</A></LI>
											</UL>
										</LI>
									</UL></LI>
							</UL>
						</SECTION>
						<!-- /.block -->
						<SECTION class="block block-system block-menu"
							id="block-system-main-menu">
							<UL class="menu nav">
								<LI class="first collapsed menu-1949"><A
									title="Supporting New Zealand's research sector"
									href="https://projects.nesi.org.nz/services">Services<SPAN>Supporting
											New Zealand's research sector</SPAN></A></LI>
								<LI class="collapsed menu-1592"><A
									title="What, Who and Why"
									href="https://projects.nesi.org.nz/about-us">About us<SPAN>What,
											Who and Why</SPAN></A></LI>
								<LI class="leaf menu-832"><A
									title="Activity within the Research Community"
									href="https://projects.nesi.org.nz/community">Community<SPAN>Activity
											within the Research Community</SPAN></A></LI>
								<LI class="last leaf menu-1685"><A
									title="Fill out an application"
									href="https://www.nesi.org.nz/apply">Apply for access<SPAN>Fill
											out an application</SPAN></A></LI>
							</UL>
						</SECTION>
						<!-- /.block -->
					</NAV>
				</DIV>
				<!-- end nav-collapse -->
			</DIV>
			<!-- end container -->
		</DIV>
		<!-- end navbar-inner -->
	</DIV>
	<!-- end navbar -->
</BODY>
</HTML>