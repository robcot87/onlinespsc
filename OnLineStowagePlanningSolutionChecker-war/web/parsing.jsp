<%-- 
    Document   : parsing.jsp
    Created on : 9-ott-2013, 12.37.37
    Author     : Rob
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.io.ObjectOutputStream"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="wrapper.jsp" %>
<div class="container">

    <div class="row-fluid">

        <div class="span6" style="alignment-adjust: auto">
            <p>STACKS</p>
            <table class="table table-bordered table-condensed table-striped">
                <tr>
                    <td>Bay</td>
                    <td>Location</td>
                    <td>Stack</td>
                    <td>Russian</td>
                    <td>Level</td>
                    <td>WeightAft</td>
                    <td>WeightFore</td>
                    <td>Weight40</td>
                    <td>HeightAft</td>
                    <td>HeightFore</td>
                    <td>lcgAft</td>
                    <td>lcgFore</td>
                    <td>lcg40</td>
                    <td>vcgAft</td>
                    <td>tcgAft</td>
                    <td>tcgFore</td>
                </tr>
              
            <%
                JSONArray[] stacks = (JSONArray[])request.getAttribute("stacks");
           
                for(JSONArray jsa : stacks){%>
                <tr>
                    <%
                    for(int i=0;i<16;i++){
                        double selected = (Double)jsa.get(i);%>
                        <td><%=selected%></td>
                    <%}%>
                 </tr>
                <%}
           %>
            </table>
            <hr>
            <p>CELLS</p>
            
            <table class="table table-bordered table-condensed table-striped">
                <tr>
                
                    <td>Bay</td>
                    <td>Stack</td>
                    <td>Tier</td>
                    <td>Location</td>
                    <td>ReeferFore</td>
                    <td>ReeferArt</td>
                    <td>cap20Aft</td>
                    <td>cap20Fore</td>
                    <td>cap40</td>
                    <td>cap45</td>
                    
                </tr>
              
             
            
            <%
                JSONArray[] cells = (JSONArray[])request.getAttribute("cells");
                
                for(JSONArray jsacell : cells){%>
                <tr>
                    <%
                    for(int i=0;i<10;i++){
                        double selected = (Double)jsacell.get(i);%>
                        <td><%=selected%></td>
                    <%}%>
                 </tr>
                <%}
           %>
            </table>
            
        </div>
      
        <a href="Controller?button=layout_vessel">
            <button class="btn btn-inverse btn-small">Design Layout Vessel</button>
         </a>

    </div>