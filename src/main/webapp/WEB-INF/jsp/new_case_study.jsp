<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="js/jquery-ui-1.12.1/jquery-ui.css">
<DIV class="container">
	<DIV class="row-fluid">
		<DIV class="breadcrumbs">
			<H2 class="element-invisible">You are here</H2>
			<DIV class="breadcrumb contextual-links-region">
				<SPAN class="inline odd first last"><A href="case_studies">Case
						Studies</A></SPAN>
			</DIV>
		</DIV>
	</DIV>
	<DIV class="row-fluid">
		<SECTION class="span12">
			<!--<a id="main-content"></a>-->

			<INPUT id="post_url" type="hidden"
				value="/nesi-config/researcher/2118/post">
			<!--  <DIV id="body">-->
			<!--  <A onclick="return (confirm('Are you sure you want to delete this case study?'))"
href="#">Delete</A>-->
			<c:choose>
				<c:when test="${edit == false}">
					<H1 class="page-header">Add Case Study</H1>
					<p style="color: red;">${errormsg}</p>
					<form:form modelAttribute="casestudy" action="new_case_study"
						method="post">
						<input type="hidden" id="projectCodes" name="projectCodes"
							value="${projectCodes}" />
						<fieldset class="panel panel-default form-wrapper"
							id="edit-group-team">
							<!--  <legend class="panel-heading"><div class="panel-title fieldset-legend">New Case Study</div></legend>-->
							<div class="panel-body">
								<div
									class="form-type-textfield form-item-field-prc-pi-name form-item form-group">
									<form:label for="edit-field-prc-pi-name" path="name">Case Study Name<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:input class="form-control form-text required" path="name"
										type="text" id="name" name="casestudy-name" size="120"
										maxlength="400" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="project">Project Code<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:input class="form-control form-text required"
										path="project" type="text" id="project" name="project"
										size="60" maxlength="128" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="requestor">Requester<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<!--<form:input class="form-control form-text required" path="requestor" value="${requestScope.cn}" type="text" id="requestor" name="requestor" size="60" maxlength="128" />-->
									<form:select class="form-control form-text required"
										path="requestor" type="text" id="requestor" name="requestor"
										maxlength="128">
										<form:option label="--unassigned--" value="0" />
										<form:options items="${requesterList}" itemValue="id"
											itemLabel="fullName" />
									</form:select>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="researchers">Researcher/s<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:select multiple="true"
										class="form-control form-text required" path="researchers"
										type="text" id="researchers" name="researchers" size="6"
										maxlength="128" disabled="true">
										<!--<form:options items="${researcherList}" itemValue="id"
											itemLabel="fullName" />-->
									</form:select>
								</div>
								<h4>Please select the contact person and the main researcher from above researchers</h4>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="mainResearcher">Contact Person<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:select class="form-control form-text required"
										path="contactPerson" type="text" id="contactPerson"
										name="contactPerson" maxlength="128">
										<form:option label="--unassigned--" value="0" />
									</form:select>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="mainResearcher">Main Researcher</form:label>
									<form:select class="form-control form-text required"
										path="mainResearcher" type="text" id="mainResearcher"
										name="mainResearcher" maxlength="128">
										<form:option label="--unassigned--" value="0" />
									</form:select>
								</div>
								<div>
									<input type="submit" value="Submit" /> <input type="button"
										onclick="location.href='case_studies';" value="Cancel" />
								</div>
							</div>
						</fieldset>
					</form:form>
				</c:when>
				<c:otherwise>
					<H1 class="page-header">Edit Case Study</H1>
					<p style="color: red;">${errormsg}</p>
					<form:form modelAttribute="casestudy" action="edit_case_study"
						method="post">
						<input type="hidden" id="projectCodes" name="projectCodes"
							value="${projectCodes}" />
						<fieldset class="panel panel-default form-wrapper"
							id="edit-group-team">
							<div class="panel-body">
								<div style="display: none;"
									class="form-type-textfield form-item-field-prc-pi-name form-item form-group">
									<form:label for="edit-field-prc-pi-name" path="name">Case Study id<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:input class="form-control form-text required" path="id"
										type="text" id="id" name="id" size="120" maxlength="400"
										readonly="true" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-name form-item form-group">
									<form:label for="edit-field-prc-pi-name" path="name">Case Study Name<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:input class="form-control form-text required" path="name"
										type="text" id="name" name="casestudy-name" size="120"
										maxlength="400" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="project">Project Code<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:input class="form-control form-text required"
										path="project" type="text" id="project" name="project"
										size="60" maxlength="128" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="requestor">Requester<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:select class="form-control form-text required"
										path="requestor" items="${requesterList}" itemValue="id"
										itemLabel="fullName" type="text" id="requestor"
										name="requestor" maxlength="128">

									</form:select>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="researchers">Researcher/s<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:select multiple="true"
										class="form-control form-text required" path="researchers"
										type="text" id="researchers" name="researchers" size="6"
										maxlength="128" disabled="true">
										<!--<form:options items="${editResearchers}" selected="selected"
											itemValue="id" itemLabel="fullName" />-->
										<form:options items="${researcherList}" selected="selected" itemValue="id"
											itemLabel="fullName" />
									</form:select>
								</div>
								<h4>Please select the contact person and the main researcher from above researchers</h4>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="mainResearcher">Contact Person<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:select class="form-control form-text required"
										path="contactPerson" type="text" id="contactPerson"
										name="contactPerson" maxlength="128">
										<form:option label="--unassigned--" value="0" />
										<form:options items="${researcherList}" itemValue="id"
											itemLabel="fullName" />
									</form:select>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="mainResearcher">Main Researcher</form:label>
									<form:select class="form-control form-text required"
										path="mainResearcher" type="text" id="mainResearcher"
										name="mainResearcher" maxlength="128">
										<form:option label="--unassigned--" value="0" />
										<form:options items="${researcherList}" itemValue="id"
											itemLabel="fullName" />
									</form:select>
								</div>
								<div class="container-inline-date">
									<div
										class="form-type-date-popup form-item-field-prc-grant-start-date form-item form-group"
										data-toggle="tooltip"
										title="When was the case study requested">
										<div id="edit-field-prc-grant-start-date" class="date-popup">
											<div
												class="form-type-textfield form-item-field-prc-grant-start-date-date form-item form-group"
												data-toggle="tooltip" title=" E.g., 18-02-2017">
												<form:label
													for="edit-field-prc-grant-start-date-datepicker-popup-0"
													path="requested">Requested<span
														class="form-required" title="This field is required."></span>
												</form:label>
												<form:input class="form-control form-text" path="requested"
													type="text"
													name="field-prc-grant-start-date[date]" id="requested" value="" size="20"
													maxlength="30" />
											</div>
										</div>
									</div>
								</div>
								<div class="container-inline-date">
									<div
										class="form-type-date-popup form-item-field-prc-grant-start-date form-item form-group"
										data-toggle="tooltip"
										title="When was the case study requested">
										<div id="edit-field-prc-grant-start-date" class="date-popup">
											<div
												class="form-type-textfield form-item-field-prc-grant-start-date-date form-item form-group"
												data-toggle="tooltip" title=" E.g., 18-02-2017">
												<form:label
													for="edit-field-prc-grant-start-date-datepicker-popup-0"
													path="requested">In progress/Rejected<span
														class="form-required" title="This field is required."></span>
												</form:label>
												<c:choose>
												    <c:when test="${isReject == true}">
														<form:input class="form-control form-text" path="rejected"
															type="text"
															name="field-prc-grant-start-date[date]" id="rejected" value="" size="20"
															maxlength="30" />												        
												    </c:when>
												    <c:otherwise>
														<form:input class="form-control form-text" path="inprogress"
															type="text"
															name="field-prc-grant-start-date[date]" id="inprogress" value="" size="20"
															maxlength="30" />												    
												    </c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>
								<div class="container-inline-date">
									<div
										class="form-type-date-popup form-item-field-prc-grant-start-date form-item form-group"
										data-toggle="tooltip"
										title="When was the case study requested">
										<div id="edit-field-prc-grant-start-date" class="date-popup">
											<div
												class="form-type-textfield form-item-field-prc-grant-start-date-date form-item form-group"
												data-toggle="tooltip" title=" E.g., 18-02-2017">
												<form:label
													for="edit-field-prc-grant-start-date-datepicker-popup-0"
													path="requested">Approved/Withdrawn<span
														class="form-required" title="This field is required."></span>
												</form:label>
												<c:choose>
												    <c:when test="${isWithdrawn == true}">
														<form:input class="form-control form-text" path="withdrawn"
															type="text"
															name="field-prc-grant-start-date[date]" id="withdrawn" value="" size="20"
															maxlength="30" />												        
												    </c:when>
												    <c:otherwise>
														<form:input class="form-control form-text" path="approved"
															type="text"
															name="field-prc-grant-start-date[date]" id="approved" value="" size="20"
															maxlength="30" />												    
												    </c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>
								<div class="container-inline-date">
									<div
										class="form-type-date-popup form-item-field-prc-grant-start-date form-item form-group"
										data-toggle="tooltip"
										title="When was the case study requested">
										<div id="edit-field-prc-grant-start-date" class="date-popup">
											<div
												class="form-type-textfield form-item-field-prc-grant-start-date-date form-item form-group"
												data-toggle="tooltip" title=" E.g., 18-02-2017">
												<form:label
													for="edit-field-prc-grant-start-date-datepicker-popup-0"
													path="requested">Published<span
														class="form-required" title="This field is required."></span>
												</form:label>
												<form:input class="form-control form-text" path="published"
													type="text"
													name="field-prc-grant-start-date[date]" value="" id="published" size="20"
													maxlength="30" />
											</div>
										</div>
									</div>
								</div>
								<div class="container-inline-date">
									<div
										class="form-type-date-popup form-item-field-prc-grant-start-date form-item form-group"
										data-toggle="tooltip"
										title="When was the case study requested">
										<div id="edit-field-prc-grant-start-date" class="date-popup">
											<div
												class="form-type-textfield form-item-field-prc-grant-start-date-date form-item form-group"
												data-toggle="tooltip" title=" E.g., 18-02-2017">
												<form:label
													for="edit-field-prc-grant-start-date-datepicker-popup-0"
													path="status">Status<span
														class="form-required" title="This field is required."></span>
												</form:label>
												<form:select class="form-control form-text required"
													path="status" type="text" id="status" name="status"
													maxlength="128">
													<form:options items="${statusList}" itemValue="id"
														itemLabel="status" />
												</form:select>
											</div>
										</div>
									</div>
								</div>
								<div class="container-inline-date">
									<div
										class="form-type-date-popup form-item-field-prc-grant-start-date form-item form-group"
										data-toggle="tooltip"
										title="When was the case study requested">
										<div id="edit-field-prc-grant-start-date" class="date-popup">
											<div
												class="form-type-textfield form-item-field-prc-grant-start-date-date form-item form-group"
												data-toggle="tooltip" title=" E.g., 18-02-2017">
												<form:label
													for="edit-field-prc-grant-start-date-datepicker-popup-0"
													path="url">URL </form:label>
												<form:input class="form-control form-text" path="url"
													type="text"
													id="edit-field-prc-grant-start-date-datepicker-popup-0"
													name="field-prc-grant-start-date[date]" value="" size="20"
													maxlength="30" />
											</div>
										</div>
									</div>
								</div>
								<div>
									<input type="submit" value="Apply" /> <input type="button"
										onclick="location.href='case_studies';" value="Close" />
								</div>
							</div>
						</fieldset>
					</form:form>
				</c:otherwise>
			</c:choose>
		</SECTION>
	</DIV>
</DIV>
<SCRIPT>
	var _gaq = _gaq || [];
	_gaq.push([ "_setAccount", "UA-1896366-19" ]);
	_gaq.push([ "_gat._anonymizeIp" ]);
	_gaq.push([ "_trackPageview" ]);
	_gaq.push(["_trackEvent","Messages","Status message","Click any field to edit it. Fields are automatically saved into the database and are updated on this page once per second. " ]);
	(function() {
		var ga = document.createElement("script");
		ga.type = "text/javascript";
		ga.async = true;
		ga.src = ("https:" == document.location.protocol ? "https://ssl"
				: "http://www")
				+ ".google-analytics.com/ga.js";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(ga, s);
	})();
</SCRIPT>
<FOOTER>
<script src="js/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="js/validator.js"></script>
<script src="js/new_case_study.js"></script>
<%@include file="footer.jsp"%>