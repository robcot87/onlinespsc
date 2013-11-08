<%-- 
    Document   : parsingOk.jsp
    Created on : 5-nov-2013, 9.53.42
    Author     : Rob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="wrapper.jsp" %>
<script>
    var myApp;
    myApp = myApp || (function () {
    var pleaseWaitDiv = $('<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"><div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div></div></div>');
    return {
        showPleaseWait: function() {
            pleaseWaitDiv.modal();
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();
</script> 
<div class="container">

   <div class="row-fluid">
  
    <div class="span6" style="alignment-adjust: auto">
        
        <p>Vessel Profile loading successful</p>
        
         <a href="Controller?button=vessel_view">
            <button class="btn btn-inverse btn-small">Vessel View</button>
         </a>
       <a href="Controller?button=parsingContainer">
           <button class="btn btn-inverse btn-small" onclick=myApp.showPleaseWait()>Add Containers</button>
       </a>
        
    </div>
</div>
<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false">
        <div class="modal-header">
            <h1>Processing...</h1>
        </div>
        <div class="modal-body">
            <div class="progress progress-striped active">
                <div class="bar" style="width: 100%;"></div>
            </div>
        </div>
    
</div>