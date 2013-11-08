

<%@page import="vessel_profile.BayCell"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.awt.Point"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : vessel_view
    Created on : 28-ott-2013, 10.34.23
    Author     : Rob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="wrapper.jsp" %>

<html xml:lang="it" lang="it" xmlns="http://www.w3.org/1999/xhtml">
<head>

<script type="text/javascript" src="raphael.js"></script>
<script type="text/javascript" src="fill_containers.js"></script>
<script type="text/javascript" src="design_vessel.js"></script>
</head>


<%   session = request.getSession();
     List<Double> bays = (List<Double>)request.getAttribute("bays");
     String[][] matrixStackTierAft=null;
     String[][] matrixStackTierFore=null;
     int tierOkOnAft=0;
     int tierKOOnAft=0;
     int tierOkBelowAft=0;
     int tierKOBelowAft=0;
     int tierOkOnFore = 0;
     int tierKOOnFore=0;
     int tierOkBelowFore=0;
     int tierKOBelowFore=0;
     int startTierIntAft=0;
     int startTierIntFore=0;
      
     int nb_layout_tiers=(Integer)session.getAttribute("nb_layout_tiers");
     int hatch_tier_idx=(Integer)session.getAttribute("hatch_tier_idx");
     Map destinationPortsMapLocal;
     //Map coloursMap = (HashMap<Integer,Integer>) session.getAttribute("coloursMap"); 
     
     int port;
     //String colourInMap;
     //String link;
 %>


<div class="container">

    <div class="row-fluid">
  
    <div class="span12" style="float:left; margin-left: 0px">
        
        <ul class="nav nav-tabs">
            <li style="width:33%"><a href="#container" data-toggle="tab">Container</a></li>
            <li style="width:33%"><a href="#stability" data-toggle="tab">Stability</a></li>
            <li style="width:33%"><a href="#problems" data-toggle="tab">Problems</a></li>
        </ul>
        
        
        <div class="tab-content">
            <div class="tab-pane active" id="container">
            <div id="bay_view">
<%   for(Double bay:bays){

          matrixStackTierAft=(String[][])session.getAttribute("matrixAFT"+bay.toString());
          matrixStackTierFore=(String[][])session.getAttribute("matrixFORE"+bay.toString());
          destinationPortsMapLocal = (HashMap<BayCell,Integer>) session.getAttribute("destinationPortsMap"+bay.toString()); 
          
          Iterator iter;
  
          
%>
          <a href="Controller?button=show_bay&id_bay=<%=bay.doubleValue()%>">
          <div class="bay_box">
              <div id="bay_box_container">
              <p style="font-weight: bold; text-align: center">BAY <%=bay.toString()%></p>
              
              <div class="bay_view_aft">
              <%System.out.println("Bay = " + bay.toString());
                for(int i=0;i<matrixStackTierAft.length;i++){%> <!-- stacks number -->
                    <div class="wrapper_general_view">
                            <ul class="images_general_view" style="list-style:none">
      <!-- tiers number --><%for(int z=0;z < nb_layout_tiers;z++){
                            // System.out.println("valore matrixAft["+i+"]["+z+"] = " +  matrixStackTierAft[i][z]);
                                //port=4;
                                //colourInMap = Integer.toString(coloursMap.get(port));
                                
                                //link="img/container_square20_little"+colourInMap+".svg";
                                
                                //System.out.println("Link = "+ link);
                                if(!matrixStackTierAft[i][z].equals("cap0")){
                                    if(!matrixStackTierAft[i][z].contains("Container")){%>
                                        <li><img src="img/container_square20_little.png"></li>
                                    <%}else{
                                        port=0; 
                                        BayCell bc = new BayCell(bay,i,z);
                                        iter = destinationPortsMapLocal.entrySet().iterator();
                                         while (iter.hasNext()) {
                                            Map.Entry mEntry = (Map.Entry) iter.next();
                                            BayCell bcFromMap = (BayCell)mEntry.getKey();
                                            if(bc.equals(bcFromMap)){
                                                port = (Integer)mEntry.getValue();
                                                break;
                                            }
                                         }
                                        if(port==4){%>
                                            <li><img src="img/container_square20_little1.svg"></li>
                                        <%}else if(port==5){%>
                                            <li><img src="img/container_square20_little2.svg"></li>
                                        <%}else if(port==6){%>
                                            <li><img src="img/container_square20_little3.svg"></li>
                                        <%}else if(port==7){%>
                                            <li><img src="img/container_square20_little4.svg"></li>
                                         <%}else if(port==8){%>
                                            <li><img src="img/container_square20_little5.svg"></li>
                                    <%}}
                                }else{%>
                                    <li></li>
                               <%}
                            }%>
                            </ul>       
                    </div>
             <%}%>
             </div>
            
              <div class="bay_view_fore">
              <%for(int i=0;i<matrixStackTierFore.length;i++){%> <!-- stacks number -->
                    <div class="wrapper_general_view">
                            <ul class="images_general_view" style="list-style:none">
      <!-- tiers number --><%for(int z=0;z < nb_layout_tiers;z++){
                                if(!matrixStackTierFore[i][z].equals("cap0")){
                                    if(!matrixStackTierFore[i][z].contentEquals("Container")){%>
                                        <li><img src="img/container_square20_little.png"></li>
                                    <%}
                                    else{%>
                                        <li><img src="img/container_square20_littleContainer.png"></li>
                                    <%}
                                 }else{%>
                                    <li></li>
                               <%}
                            }%>
                            </ul>       
                    </div>
             <%}%>
             </div>       
             </div>
             </div>
        </a>
    <%}%>
            
    </div>
    </div>
            <div class="tab-pane" id="stability">tab stability</div>
            <div class="tab-pane" id="problems">tab problems</div>
        </div>
      
    </div>
</div></div>
</html>


