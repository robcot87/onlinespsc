<%-- 
    Document   : index
    Created on : 8-ott-2013, 13.45.01
    Author     : Rob
--%>
<%@page import="java.util.LinkedList"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="vessel_profile.StackManagerLocal"%>
<%@page import="vessel_profile.Cell"%>
<%@page import="vessel_profile.Stack"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="wrapper.jsp" %>


<%       
          
          String idbay = (String)request.getAttribute("id_bay");
          int nb_layout_stacks = 13;
          int nb_layout_tiers=(Integer)session.getAttribute("nb_layout_tiers");
          int hatch_tier_idx=(Integer)session.getAttribute("hatch_tier_idx");
         
          int indexBay;
          List<Double> bays = (List<Double>)request.getAttribute("bays"); 
          
          String[][] matrixStackTierAft = (String[][])session.getAttribute("matrixAFT"+idbay);
          String[][] matrixStackTierFore = (String[][]) session.getAttribute("matrixFORE"+idbay);
          /*
          int tierOkOnAft =(Integer) request.getAttribute("tierOkOnAft");
          int tierKOOnAft = (Integer)request.getAttribute("tierKOOnAft");
          int tierOkBelowAft = (Integer)request.getAttribute("tierOkBelowAft");
          int tierKOBelowAft = (Integer)request.getAttribute("tierKOBelowAft");
          int tierOkOnFore = (Integer)request.getAttribute("tierOkOnFore");
          int tierKOOnFore=(Integer)request.getAttribute("tierKOOnFore");
          int tierOkBelowFore=(Integer)request.getAttribute("tierOkBelowFore");
          int tierKOBelowFore=(Integer)request.getAttribute("tierKOBelowFore");
          int startTierIntAft = (Integer)request.getAttribute("startTierIntAft");
          int startTierIntFore= (Integer)request.getAttribute("startTierIntFore");
          * */
    %>     
          
          
          
          

<div class="container">

    <div class="row-fluid">
       
        <!-- BAY SIDEBAR -->
        <div class="span1" >
             <ul class="nav nav-list bs-docs-sidenav">
                 <li><a href="Controller?button=vessel_view"> 
                    <button class="btn btn-mini btn-inverse btn-block" style="width:40px">Vessel View</button> </a>
                 </li>
                 <%for(Double b:bays){
                     indexBay=(int)b.doubleValue();
                 %>
                 <li><a href="Controller?button=show_bay&id_bay=<%=b.doubleValue()%>"> 
                    <button class="btn btn-mini btn-inverse btn-block" style="width:40px">Bay <%=indexBay%></button> </a>
                 </li>
                 <%}%>       
            </ul>
        </div>

            
<div class="span11" >
    <p style="font-weight: bold; text-align: center;font-size: medium  ">BAY <%=idbay%></p>
</div>

    <!-- ************************************* AFT*************************************************** -->   
   <div class="span11" >
       <div class="aft" >
        <p style="font-weight: bold; text-align: center">AFT</p>
        <!--Index Tiers-->
        <div class="wrapper">
        <ul class="immagini" style="list-style:none">
            <%for(int z=0;z < nb_layout_tiers;z++){
                //if((z>=startTierIntAft && z <= tierOkOnAft && z< hatch_tier_idx) || (z >= hatch_tier_idx && z < nb_layout_tiers-tierKOBelowAft)){
            %>
            <li><p style="font-weight: bold; text-align: center"><%=z+1%></p></li>
            <%//}
            }%>
        </ul>
        </div>
        
        
        <%for(int k=0;k< matrixStackTierAft.length;k++){%>
        <div class="wrapper">
        <ul class="immagini" style="list-style:none">
            
            <% 
            for(int z=0;z < nb_layout_tiers;z++){
                //System.out.println("valore matrixAft["+k+"]["+z+"] = " +  matrixStackTierAft[k][z]);
                //if((z>=startTierIntAft && z<= tierOkOnAft && z< hatch_tier_idx) || (z >= hatch_tier_idx && z < nb_layout_tiers-tierKOBelowAft)){
                if(matrixStackTierAft[k][z].equals("cap20")){%>
                    <!--<li><img src="img/container_square20.svg"></li>-->
                    <li><img src="img/container_squareX40Aft1.svg"></li>
                <%}else if(matrixStackTierAft[k][z].equals("cap40")){%>
                    <!--<li><img src="img/container_squareX40Aft.svg"></li>-->
                    <li><img src="img/container_squareX40Aft1.svg"></li>
                <%}else if(matrixStackTierAft[k][z].equals("cap40reefer")){%>
                    <!--<li><img src="img/container_squareX40AftReefer.svg"></li> -->
                    <li><img src="img/container_squareX40Aft1.svg"></li>
                <%}else if(matrixStackTierAft[k][z].equals("cap20reefer")){%>
                    <!--<li><img src="img/container_square20reefer.svg"></li> -->
                    <li><img src="img/container_squareX40Aft1.svg"></li>
                <%}else if(matrixStackTierAft[k][z].equals("cap40Container")){%>
                    <li><img src="img/container_squareX40Aft1Container.svg"></li>  
                <%}else if(matrixStackTierAft[k][z].equals("cap40reeferContainer")){%>
                   <li><img src="img/container_squareX40Aft1Container.svg"></li>  
                <%}else if(matrixStackTierAft[k][z].equals("cap20Container")){%>
                    <li><img src="img/container_squareX40Aft1Container.svg"></li>  
                <%}else if(matrixStackTierAft[k][z].equals("cap20reeferContainer")){%>
                    <li><img src="img/container_squareX40Aft1Container.svg"></li>  
                <%}else if(matrixStackTierAft[k][z].equals("cap0")){%>
                    <li></li>
              <%}
                //}
             }%>
      </ul>
      </div>
      <%}%>
      </div>
   
    <!-- ************************************* FORE*************************************************** -->   
       <div class="fore" >
        <p style="font-weight: bold; text-align: center">FORE</p>
        <!--Index Tiers-->
        <div class="wrapper">
        <ul class="immagini" style="list-style:none">
            
            <%for(int z=0;z <= nb_layout_tiers;z++){
               // if((z>=startTierIntFore && z <= tierOkOnFore && z< hatch_tier_idx) || (z >= hatch_tier_idx && z < nb_layout_tiers-tierKOBelowFore)){
            %>
                <li><p style="font-weight: bold; text-align: center"><%=z+1%></p></li>
            <%//}
            }%>
        </ul>
        </div>
        
        
        <%
        for(int k=0;k< matrixStackTierFore.length;k++){%>
        <div class="wrapper">
        <ul class="immagini" style="list-style:none">
            
           <%for(int z=0;z < nb_layout_tiers;z++){
               // if((z>=startTierIntFore && z<= tierOkOnFore && z< hatch_tier_idx) || (z >= hatch_tier_idx && z < nb_layout_tiers-tierKOBelowFore)){
                
                    if(matrixStackTierFore[k][z].equals("cap20")){%>
                        <!--<li><img src="img/container_square20.svg"></li>-->
                        <li><img src="img/container_squareX40Aft1.svg"></li>
                    <%}else if(matrixStackTierFore[k][z].equals("cap40")){%>
                        <!--<li><img src="img/container_squareX40Fore.svg"></li>-->
                        <li><img src="img/container_squareX40Aft1.svg"></li>
                    <%}else if(matrixStackTierFore[k][z].equals("cap40reefer")){%>
                        <!--<li><img src="img/container_squareX40ForeReefer.svg"></li> -->
                        <li><img src="img/container_squareX40Aft1.svg"></li>
                    <%}else if(matrixStackTierFore[k][z].equals("cap20reefer")){%>
                        <!--<li><img src="img/container_square20reefer.svg"></li> -->
                        <li><img src="img/container_squareX40Aft1.svg"></li>
                    <%}else if(matrixStackTierFore[k][z].equals("cap40Container")){%>
                        <li><img src="img/container_squareX40Aft1Container.svg"></li>  
                    <%}else if(matrixStackTierFore[k][z].equals("cap40reeferContainer")){%>
                        <li><img src="img/container_squareX40Aft1Container.svg"></li>  
                    <%}else if(matrixStackTierFore[k][z].equals("cap20Container")){%>
                        <li><img src="img/container_squareX40Aft1Container.svg"></li>  
                    <%}else if(matrixStackTierFore[k][z].equals("cap20reeferContainer")){%>
                        <li><img src="img/container_squareX40Aft1Container.svg"></li>
                    <%}else if(matrixStackTierFore[k][z].equals("cap0")){%>
                        <li class="transparent"></li>     
                  <%}
                //}
             }%>
      </ul>
      </div>
      <%}%>
      </div>

</div>
      
</div>
</div>