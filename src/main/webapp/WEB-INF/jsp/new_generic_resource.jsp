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
				<SPAN class="inline odd first last"><A href="scientific_programmer">Scientific Programmers</A></SPAN>
			</DIV>
		</DIV>
	</DIV>
	<DIV class="row-fluid">
		<SECTION class="span12">
			<INPUT id="post_url" type="hidden"
				value="/nesi-config/researcher/2118/post">
			<c:choose>
				<c:when test="${edit == false}">
					<H1 class="page-header">Add a new entry for scientific programmer usage</H1>
					<p style="color: red;">${errormsg}</p>
					<form:form modelAttribute="genericresource" action="add_scientific_programmer_usage"
						method="post">
						<input type="hidden" id="projectCodes" name="projectCodes"
							value="${projectCodes}" />
						<fieldset class="panel panel-default form-wrapper"
							id="edit-group-team">
							<div class="panel-body">
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="adviser">Adviser<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:select class="form-control form-text required"
										path="adviser" type="text" id="adviser" name="adviser"
										maxlength="128">
										<form:options items="${adviserList}" itemValue="id" itemLabel="fullName" />
									</form:select>
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
									<form:label for="edit-field-prc-pi-email" path="resourceInstance">Programmer<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:select class="form-control form-text required"
										path="resourceInstance" type="text" id="resourceInstance" name="resourceInstance"
										maxlength="128">
										<form:options items="${resourceList}" itemValue="id" itemLabel="description" />
									</form:select>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="value">Value<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:input class="form-control form-text required"
										path="value" type="text" id="value" name="value"
										size="60" maxlength="128" /><span style="padding-left: 8px;">hours</span>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="date">Date<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:input class="form-control form-text required"
										path="date" type="text" id="date" name="date"
										size="60" maxlength="128" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="note">Note<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:textarea path="note" rows="5" cols="30" />
								</div>
								<div>
									<input type="submit" value="Submit" /> <input type="button"
										onclick="location.href='scientific_programmer';" value="Cancel" />
								</div>
							</div>
						</fieldset>
					</form:form>
				</c:when>
				<c:otherwise>
					<H1 class="page-header">Edit Scientific Programmer Consultant Resource application</H1>
					<p style="color: red;">${errormsg}</p>
					<form:form modelAttribute="resourceProjectUsage" action="edit_scientific_programmer_usage"
						method="post">
						<input type="hidden" id="projectCodes" name="projectCodes"
							value="${projectCodes}" />
						<fieldset class="panel panel-default form-wrapper"
							id="edit-group-team">
							<div class="panel-body">
								<div style="display: none;"
									class="form-type-textfield form-item-field-prc-pi-name form-item form-group">
									<form:label for="edit-field-prc-pi-name" path="id">id
									</form:label>
									<form:input class="form-control form-text required" path="id"
										type="text" id="id" name="id" size="120" maxlength="400"
										readonly="true" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="adviser">Adviser<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:select class="form-control form-text required"
										path="adviser" type="text" id="adviser"
										name="adviser" maxlength="128">
										<form:option value="${selAdviserId}" label="${selAdviserName}" selected="true"/>
										<form:options items="${adviserList}" itemValue="id" itemLabel="fullName" />
									</form:select>
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
									<form:label for="edit-field-prc-pi-email" path="resourceInstance">Programmer<span
											class="form-required" style="color: red;" title="This field is required.">*</span>
									</form:label>
									<form:select class="form-control form-text required"
										path="resourceInstance" type="text" id="resourceInstance" name="resourceInstance"
										maxlength="128">
										<form:option value="${selResourceId}" label="${selResourceName}" selected="true"/>
										<form:options items="${resourceList}" itemValue="id" itemLabel="description" />
									</form:select>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="value">Value<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:input class="form-control form-text required"
										path="value" type="text" id="value" name="value"
										size="60" maxlength="128" /><span style="padding-left: 8px;">hours</span>
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="date">Date<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:input class="form-control form-text required"
										path="date" type="text" id="date" name="date"
										size="60" maxlength="128" />
								</div>
								<div
									class="form-type-textfield form-item-field-prc-pi-email form-item form-group">
									<form:label for="edit-field-prc-pi-email" path="note">Note<span
											class="form-required" style="color: red;" title="This field is required."></span>
									</form:label>
									<form:textarea path="note" rows="5" cols="30" />
								</div>
								<div>
									<input type="submit" value="Submit" /> <input type="button"
										onclick="location.href='scientific_programmer';" value="Cancel" />
								</div>
							</div>
						</fieldset>
					</form:form>
				</c:otherwise>
			</c:choose>
		</SECTION>
	</DIV>
</DIV>
<FOOTER>
<script src="js/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="js/validator.js"></script>
<script src="js/new_generic_resource.js"></script>
<%@include file="footer.jsp"%>