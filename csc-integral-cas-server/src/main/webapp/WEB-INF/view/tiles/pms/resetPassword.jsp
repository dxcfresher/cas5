<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
   function submit(url) {
    document.resetPasswordForm.action.value="resetPassword";
    document.resetPasswordForm.submit();
   }
   function submitonenter(evt,thisObj) {
       evt = (evt) ? evt : ((window.event) ? window.event : "")
      if (evt) {
           if ( evt.keyCode==13 || evt.which==13 ) {
               submit();
           }
       }
   }
</script>

   <div class="secondColumn"></div>
   <div class="rb_content">

      <form:form modelAttribute="resetPasswordForm" name="resetPasswordForm" method="POST">
      <input type="hidden" name="action" value="N">

          <div class="rb_content_top headerTxt">
            <p style="margin: 0px;">
              <spring:message code="screen.resetPassword.title"/>
            </p>
          </div>
          <div class="rb_content_middle" style="height: 300px;">

          <div style="padding: 15px;">
            <table style="margin-left:10px;">
              <tr>
                <td style="width:350px"><p class="text"><spring:message code="screen.resetPassword.userName"/></p>
                </td>
              </tr>
              <tr>
                <td>
                  <div>
                    <form:input path="userName" cssStyle="width: 260px;" onkeypress="submitonenter(event,this);"/>
                    <span class="mandatory">*</span>
                    <p class="error"><form:errors path="userName"/></p>
                  </div>
                </td>
              </tr>

			  <tr>
                <td><p class="text"><spring:message code="screen.resetPassword.systemUsername"/></p>
                </td>
                <td><p class="text"><spring:message code="screen.resetPassword.systemPassword"/></p>
                </td>
              </tr>
              <tr>
                <td>
                  <div>
                  <form:input path="systemUsername" cssStyle="width: 260px;" onkeypress="submitonenter(event,this);"/>
                  <span class="mandatory">*</span>
                  <p class="error"><form:errors path="systemUsername"/></p>
                  </div>
                </td>
                <td>
                  <div>
                  <form:password path="systemPassword" cssStyle="width: 260px;" onkeypress="submitonenter(event,this);"/>
                  <span class="mandatory">*</span>
                  <p class="error"><form:errors path="systemPassword"/></p>
                  </div>
                  </td>
              </tr>

              <tr>
              <td colspan="2">
              	<table><tr>
              		<td><div class="sectionbutton" >
						<p><a href="javascript:submit('resetPassword');">
						<spring:message code="screen.resetPassword.resetPassword"/></a></p>
	              	</div></td>
	              	<td style="padding:10px;"></td>
              		<td><div class="sectionbutton" >
						<p><a href="ams"><spring:message code="screen.cancel"/></a></p>
					</div></td>
              	</tr></table>
              </td>
              </tr>
              <tr>
              <td colspan="2">
	              <c:if test="${msgStatus != null}">
	                <div class="info" style="width:400px;"><spring:message code="${msgStatus}"/></div>
	              </c:if>
	              <c:if test="${msgError != null}">
	                <div class="error" style="width:400px;">${msgError}</div>
	              </c:if>
              </td>
              </tr>
            </table>
          </div>
      </div>

      <div class="rb_content_bottom">
        <div class="rb_left"></div>
        <div class="rb_content_bottom_area2" ></div>
        <div class="rb_right"></div>
      </div>
    </form:form>
  </div>


