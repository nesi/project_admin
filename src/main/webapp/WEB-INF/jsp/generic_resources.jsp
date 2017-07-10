<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>
<DIV class="container">
	<DIV class="row-fluid">
		<DIV class="breadcrumbs">
			<H2 class="element-invisible">You are here</H2>
			<DIV class="breadcrumb contextual-links-region">
				<SPAN class="inline odd first last"><A
					href="https://projects.nesi.org.nz/nesi-config/projects">Projects</A></SPAN>
			</DIV>
		</DIV>
	</DIV>
	<DIV class="row-fluid">		
		<SECTION class="span12">
			<H1 class="page-header">NeSI Admin</H1>
			<UL class="nav nav-tabs">
				<LI><A
					href="https://projects.nesi.org.nz/nesi-config/projects">Projects</A></LI>
				<LI><A href="https://projects.nesi.org.nz/nesi-config/kpis">Project
						KPIs</A></LI>
				<LI><A
					href="https://projects.nesi.org.nz/nesi-config/researchoutputs">Research
						Outputs</A></LI>
				<LI><A href="https://projects.nesi.org.nz/nesi-config/researchers">Researchers</A></LI>
				<LI><A
					href="https://projects.nesi.org.nz/nesi-config/advisers">Advisers</A></LI>
				<LI><A href="case_studies">Case Studies</A></LI>
				<LI class="active"><A class="active" href="/scientific_programmer">Scientific Programmers<SPAN class="element-invisible">(active tab)</SPAN>
				</A></LI>
				<LI><A href="https://projects.nesi.org.nz/nesi-config/site">Configure
						system</A></LI>
			</UL>
			<A href="add_scientific_programmer_usage">Add new entry</A>
			<H3>
				Scientific Programmers Usage <span id="recordNum">(${f:length(ojbects)})</span>
			</H3>
			<c:choose>
				<c:when test="${f:length(ojbects) gt 0}">
					<table class="tablesorter table" id="genericresources">
						<thead>
							<tr>
								<TH>#</TH>
								<TH>Project Code</TH>							
								<TH>Date</TH>
								<TH>Programmer</TH>
								<TH>Value</TH>
								<TH>Adviser</TH>
								<TH>Note</TH>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ojbects}" var="ojbect">
								<tr>
									<td><a href="edit_scientific_programmer_usage?id=${ojbect.id}">${ojbect.id}&nbsp;</a></td>
									<td>${ojbect.project}&nbsp;</a></td>
									<td>${ojbect.date}&nbsp;</td>
									<td>${ojbect.resourceInstance}&nbsp;</td>
									<td>${ojbect.value}&nbsp;hours</td>
									<td>${ojbect.adviser}&nbsp;</td>
									<td>${ojbect.note}&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div></div>
				</c:otherwise>
			</c:choose>
		</SECTION>
	</DIV>
</DIV>
<script src="js/generic_resources.js" />
<%@include file="footer.jsp"%>