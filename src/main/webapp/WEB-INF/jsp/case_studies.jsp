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
			<!--<a id="main-content"></a>-->
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
				<LI class="active"><A class="active" href="/case_studies">Case
						Studies<SPAN class="element-invisible">(active tab)</SPAN>
				</A></LI>
				<!-- <LI><A href="scientific_programmer">Scientific Programmers</A></LI>-->
				<LI><A href="https://projects.nesi.org.nz/nesi-config/site">Configure
						system</A></LI>
			</UL>
			<!-- Lwer tabs: All, Requested, Agreed, Draft, Approved, Published  -->
			<UL id="filter" class="nav nav-pills">
				<LI class="active"><A href="">All</A></LI>
				<LI><A href="">Open</A></LI>
				<LI><A href="">Closed</A></LI>
				<LI><A href="">Requested</A></LI>
				<LI><A href="">In progress</A></LI>
				<LI><A href="">Approved</A></LI>
				<LI><A href="">Published</A></LI>
				<LI><A href="">Rejected</A></LI>
				<LI><A href="">Withdrawn</A></LI>
			</UL>



			<A href="new_case_study">Create new case study</A>
			<H3>
				Case Studies <span id="recordNum">(${f:length(ojbects)})</span>
			</H3>

			<c:choose>
				<c:when test="${f:length(ojbects) gt 0}">
					<table class="tablesorter table" id="casestudy">
						<thead>
							<tr>
								<TH>#</TH>
								<TH>Name</TH>
								<TH>Project Code</TH>
								<TH>Requester</TH>
								<TH>Contact Person</TH>
								<TH>Main Researcher</TH>
								<TH>Researcher/s</TH>
								<TH>Requested</TH>
								<TH>In-progress/Rejected</TH>
								<TH>Approved/Withdrawn</TH>
								<TH>Published</TH>
								<TH>Status</TH>
								<TH>URL</TH>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ojbects}" var="ojbect">
								<c:choose>
									<c:when test="${ojbect.status == 'In progress'}">
										<tr class="Inprogress">
									</c:when>
									<c:otherwise>
										<tr class="${ojbect.status}">
									</c:otherwise>
								</c:choose>
								<td><a href="edit_case_study?id=${ojbect.id}">${ojbect.id}&nbsp;</a></td>
								<td>
									<c:choose>
										<c:when test="${empty ojbect.url}">
											${ojbect.name}&nbsp;
										</c:when>
										<c:otherwise>
											<a href="${ojbect.url}" target="_blank">${ojbect.name}&nbsp;</a>
										</c:otherwise>
									</c:choose>
								</td>
								<td><a
									href="https://projects.nesi.org.nz/nesi-config/project/${ojbect.project}">${ojbect.project}&nbsp;</a></td>
								<td>${ojbect.requestor}&nbsp;</td>
								<td>${ojbect.contactPerson}&nbsp;</td>
								<td>${ojbect.mainResearcher}&nbsp;</td>
								<td>${ojbect.researchers}&nbsp;</td>
								<td>${ojbect.requested}&nbsp;</td>
								<td>${ojbect.inprogress!=null?ojbect.inprogress:ojbect.rejected}&nbsp;</td>
								<td>${ojbect.approved!=null?ojbect.approved:ojbect.withdrawn}&nbsp;</td>
								<td>${ojbect.published}&nbsp;</td>
								<td>${ojbect.status}&nbsp;</td>
								<td><a href="${ojbect.url}">${ojbect.url}&nbsp;</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div>There is no case study in the database.</div>
				</c:otherwise>
			</c:choose>
		

		</SECTION>
	</DIV>
</DIV>
<!--  <SCRIPT src="https://projects.nesi.org.nz/sites/default/files/js/js_Rk5YIkK6m2gKTGH-GrMu0pM_PlMKXnI0ktQUgXw1XgA.js"></SCRIPT>-->
<SCRIPT src="js/case_studies.js" />
<%@include file="footer.jsp"%>