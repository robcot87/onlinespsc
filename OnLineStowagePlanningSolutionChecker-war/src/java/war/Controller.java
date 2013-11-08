package war;


import java.awt.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vessel_profile.BayCell;

import vessel_profile.Cell;
import vessel_profile.CellManagerLocal;
import vessel_profile.ContainerManagerLocal;
import vessel_profile.Stack;
import vessel_profile.StackManagerLocal;
/**
 *
 * @author Rob
 */
@WebServlet(urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    HttpSession session;
    boolean parsingStack=false;
    boolean parsingCells=false;
    boolean parsingContainers=false;
    String azione;
    ServletContext sc;
    RequestDispatcher rd;
    String group,profile_type,vessel_code;
    long nb_layout_stacks_long,nb_layout_tiers_long,hatch_tier_idx_long;
    int nb_layout_stacks,nb_layout_tiers,hatch_tier_idx;
    List<Double> bays;
    int tierOkOnAft=0;
    int tierKOOnAft=0;
    int tierOkBelowAft=0;
    int tierKOBelowAft=0;
    int tierOkOnFore = 0;
    int tierKOOnFore=0;
    int tierOkBelowFore=0;
    int tierKOBelowFore=0;
    int tierKOOnAftFromTop=0;
    int startTierIntAft=0;
    int startTierIntFore=0;
    String[][] matrixAFT,matrixFORE;
    Map destinationPortsMap;
    Map coloursMap;
    String id_bay;
    
    @EJB
    private StackManagerLocal stack_manager;
    @EJB
    private CellManagerLocal cell_manager;
    @EJB
    private ContainerManagerLocal container_manager;
    
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        
          response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
          sc = getServletContext();
          rd = null;
          azione = request.getParameter("button");
          if(azione.equals("parsing")){
              System.out.println("dentro switch button parsing");
              parsingVesselProfile(request,response);
          }
         else if (azione.equals("show_bay")){
             System.out.println("dentro switch button show bay");
              show_bay(request,response);
         }
           else if (azione.equals("index")){
              reset_to_index(request,response);
         }
         else if (azione.equals("vessel_view")){
              vessel_view(request,response);
         }
         else if (azione.equals("parsingContainer")){
              parsingContainersFile(request,response);
         }
       }

      
       private void vessel_view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
    
        session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        sc = getServletContext();
        rd = null;
        bays = stack_manager.findBays();
        request.setAttribute("bays",bays);
       
       
        rd = sc.getRequestDispatcher("/vessel_view.jsp");
        rd.forward(request, response);
       
       
       }
      
    private void reset_to_index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
           response.setContentType("text/html;charset=UTF-8");
            sc = getServletContext();
            rd = null;
        
            group="";
            profile_type="";
            vessel_code="";
            bays.clear();
            
            id_bay="";
            if(parsingStack && parsingCells && parsingContainers)
                rd = sc.getRequestDispatcher("/Controller?button=vessel_view");
            else if(parsingStack && parsingCells && !parsingContainers)
                rd = sc.getRequestDispatcher("/parsingOk.jsp");
            else
                rd = sc.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
     }
     
    private void show_bay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
    
        response.setContentType("text/html;charset=UTF-8");
        sc = getServletContext();
        rd = null;
 
        id_bay = (String) request.getParameter("id_bay");
        //request.setAttribute("group", group);
        //request.setAttribute("profile_type", profile_type);
        //request.setAttribute("vessel_code", vessel_code);      
        bays = stack_manager.findBays();
        request.setAttribute("bays", bays);
        request.setAttribute("id_bay",id_bay);
        
        rd = sc.getRequestDispatcher("/show_bay.jsp");
        rd.forward(request, response);
      
      }
      
    private void parsingVesselProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException {
    
      session = request.getSession(true);
      ParserJSON pjson;
      JSONArray cells,stacks;
      pjson = new ParserJSON();
      pjson.parsing();
      
      group = pjson.getGroup();
        
      profile_type=pjson.getProfile_Type();
        
      vessel_code=pjson.getVessel_code();
      
      nb_layout_stacks_long = pjson.getNb_layout_stacks();
      nb_layout_tiers_long = pjson.getNb_layout_tiers();
      hatch_tier_idx_long = pjson.getHatch_tier_idx();
      
      nb_layout_stacks = (int)nb_layout_stacks_long;
      nb_layout_tiers = (int)nb_layout_tiers_long;
      hatch_tier_idx= (int)hatch_tier_idx_long;
      
      
      cells = pjson.getCells();
      stacks = (JSONArray)pjson.getStacks();
      
      //1
      parsingStack(stacks);
            
      //2
      parsingCells(cells);
      
      bays = stack_manager.findBays();
      
      
      
      for(Double bay:bays){
            System.out.println("Bay number = " + bay.toString());
            
            matrixAFT=design_bay_Aft(bay.doubleValue());
            matrixFORE=design_bay_Fore(bay.doubleValue());
            destinationPortsMap = new HashMap<BayCell,Integer>();
            
            session.setAttribute("matrixAFT"+bay.toString(),matrixAFT);
            session.setAttribute("matrixFORE"+bay.toString(),matrixFORE);
            session.setAttribute("tierOkOnAft"+bay.toString(), tierOkOnAft);
            //request.setAttribute("tierKOOnAft"+bay.toString(), tierKOOnAft);
            //request.setAttribute("tierOkBelowAft"+bay.toString(), tierOkBelowAft);
            session.setAttribute("tierKOBelowAft"+bay.toString(), tierKOBelowAft);
            session.setAttribute("tierOkOnFore"+bay.toString(), tierOkOnFore);
            //request.setAttribute("tierKOOnFore"+bay.toString(), tierKOOnFore);
            //request.setAttribute("tierOkBelowFore"+bay.toString(), tierOkBelowFore);
            session.setAttribute("tierKOBelowFore"+bay.toString(), tierKOBelowFore);
            session.setAttribute("startTierIntAft"+bay.toString(), startTierIntAft);
            session.setAttribute("startTierIntFore"+bay.toString(), startTierIntFore);
            session.setAttribute("destinationPortsMap"+bay.toString(), destinationPortsMap);
            
      }
      session.setAttribute("nb_layout_tiers",nb_layout_tiers);
      session.setAttribute("hatch_tier_idx",hatch_tier_idx);
      
      rd = sc.getRequestDispatcher("/parsingOk.jsp");
      rd.forward(request, response);
      
    }
    private void parsingContainersFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException {
    
      ParserJSON pjson;
      JSONArray containers,ports,releaseContainers,containerPosition;
      pjson = new ParserJSON();
      pjson.parsing();
      
      group = pjson.getGroup();
        
      profile_type=pjson.getProfile_Type();
        
      vessel_code=pjson.getVessel_code();
      
      containers = (JSONArray)pjson.getLoadListContainers();
      
      releaseContainers=(JSONArray)pjson.getReleaseContainers();
              
      containerPosition =(JSONArray)pjson.getContainersPosition();
      
      parsingContainers(containers,releaseContainers,containerPosition);
      
      
      session.setAttribute("coloursMap",coloursMap);
      
      rd = sc.getRequestDispatcher("/parsingOkContainers.jsp");
      rd.forward(request, response);
    
    }
    
    private void parsingStack(JSONArray stacks){
    
        if(!parsingStack){
            double actualStack,actualLevel,m_weight_aft,m_weight_fore,m_weight_40,m_height_aft,m_height_fore,
               m_lcg_aft,m_lcg_fore,m_lcg_40,m_vcg_aft,m_vcg_fore,m_tcg_aft,m_tcg_fore,russian,bay,loc;
            for(Object jsa:stacks){
                m_weight_aft = (Double)((JSONArray)jsa).get(5);
                m_weight_fore = (Double)((JSONArray)jsa).get(6);
                m_weight_40 = (Double)((JSONArray)jsa).get(7);
                m_height_aft = (Double)((JSONArray)jsa).get(8);
                m_height_fore = (Double)((JSONArray)jsa).get(9);
                m_lcg_aft=(Double)((JSONArray)jsa).get(10);
                m_lcg_fore= (Double)((JSONArray)jsa).get(11);
                m_lcg_40= (Double)((JSONArray)jsa).get(12);
                m_vcg_aft= (Double)((JSONArray)jsa).get(13);
                m_vcg_fore= 0.0; //******** This value is not correct
                m_tcg_aft= (Double)((JSONArray)jsa).get(14);
                m_tcg_fore= (Double)((JSONArray)jsa).get(15);

                russian = (Double)((JSONArray)jsa).get(3);

                actualStack = (Double)((JSONArray)jsa).get(2);
                actualLevel = (Double)((JSONArray)jsa).get(4);

                bay= (Double)((JSONArray)jsa).get(0);
                loc= (Double)((JSONArray)jsa).get(1);

                stack_manager.addStack(bay,loc,m_weight_aft,m_weight_fore,m_weight_40,m_height_aft,m_height_fore,m_lcg_aft,m_lcg_fore,m_lcg_40,m_vcg_aft,m_vcg_fore,m_tcg_aft,m_tcg_fore,russian,actualLevel,actualStack);//,actualBay,actualLocation);
            }
            parsingStack=true;
        }
     }
    
    private void parsingCells(JSONArray cells){
       
        if(!parsingCells){
            List<Stack> actualStack;
            double bay,location,stack,m_reeferFore,m_reeferAft,m_cap20Aft,m_cap20Fore,m_cap40,m_cap45,tier;
            double levelFromTier;
            double hatch_tier_idx_double;
            
            for(Object jsa:cells){
                bay = (Double)((JSONArray)jsa).get(0);
                location = (Double)((JSONArray)jsa).get(3);
                stack = (Double)((JSONArray)jsa).get(1);
                tier = (Double)((JSONArray)jsa).get(2);
                hatch_tier_idx_double =(double) hatch_tier_idx;
                
                if(tier > hatch_tier_idx_double)
                    levelFromTier= 2.0;
                else
                    levelFromTier= 1.0;

                actualStack = stack_manager.findStackForCell(stack,bay,location,levelFromTier);
                m_reeferFore=(Double)((JSONArray)jsa).get(4);
                m_reeferAft=(Double)((JSONArray)jsa).get(5);
                m_cap20Aft=(Double)((JSONArray)jsa).get(6);
                m_cap20Fore=(Double)((JSONArray)jsa).get(7);
                m_cap40=(Double)((JSONArray)jsa).get(8);
                m_cap45=(Double)((JSONArray)jsa).get(9);
                cell_manager.addCell(bay,actualStack,tier,location,m_reeferFore,m_reeferAft,m_cap20Aft,m_cap20Fore,m_cap40,m_cap45);
            }
            parsingCells=true;
        }
    }
        
    private void parsingContainers(JSONArray containers,JSONArray releaseContainers,JSONArray containerPosition){
        
        if(!parsingContainers){
            long container_cap,reeferLong;
            Double container_weight;
            long container_weight_long;
            double container_height;
            String departure_port,destination_port;
            String[][]matrixStackTierAftContainers;
            String[][]matrixStackTierForeContainers;
            String stringBayValue;
            long location,stack,tier,portDestinationNumber;
            double locationDouble,stackDouble,tierDouble,bayFromLocationValue;
            List<Double> bayFromLocation;
            int portDestinationNumberInt;
            boolean reefer;
            int stackInt,tierInt;
            Map destinationPortsMapLocal;
            
            Class longClass = Long.class;
            for(Object c:containerPosition){
                JSONObject jso = (JSONObject)c;
                location= (Long)jso.get("location");
                stack= (Long)jso.get("stack");
                tier= (Long)jso.get("tier");
                portDestinationNumber= (Long)jso.get("dipo");
                if(longClass.isInstance(jso.get("weight"))){
                    container_weight_long= ((Long)jso.get("weight")).longValue();
                    container_weight = (double)container_weight_long;
                }
                else{
                    container_weight = (Double)(jso.get("weight"));
                }
                reeferLong = (Long)jso.get("reefer");
                container_cap = (Long)jso.get("length");
                locationDouble= (double)location;
                stackDouble= (Long)stack;
                tierDouble= (double)tier;
                portDestinationNumberInt= (int)portDestinationNumber;
                
                if(reeferLong == 0)
                    reefer= false;
                else
                    reefer= true;
                
                bayFromLocation = stack_manager.findBayFromLocation(locationDouble);
                //Da eliminare con i file corretti
                if(bayFromLocation.isEmpty())
                    bayFromLocationValue = 0.0; // If the location doesn't has a Bay
                else{
                    bayFromLocationValue= bayFromLocation.get(0).doubleValue();
                    stringBayValue = bayFromLocation.get(0).toString();
                    matrixStackTierAftContainers = (String[][])session.getAttribute("matrixAFT"+stringBayValue);
                    matrixStackTierForeContainers = (String[][])session.getAttribute("matrixFORE"+stringBayValue);
                    destinationPortsMapLocal = (HashMap<BayCell,Integer>)session.getAttribute("destinationPortsMap"+stringBayValue);
                    
                    session.removeAttribute("matrixAFT"+stringBayValue);
                    session.removeAttribute("matrixFORE"+stringBayValue);
                    session.removeAttribute("destinationPortsMap"+stringBayValue);
                    
                    stackInt=(int)stackDouble;
                    tierInt = (int)tierDouble;

                    if(container_cap==40){
                        if(reefer){
                            matrixStackTierAftContainers[stackInt][tierInt]="cap40reeferContainer";
                            destinationPortsMapLocal.put(new BayCell(bayFromLocationValue,stackInt,tierInt),portDestinationNumberInt);
                        }
                        else{
                            matrixStackTierAftContainers[stackInt][tierInt]="cap40Container";
                            destinationPortsMapLocal.put(new BayCell(bayFromLocationValue,stackInt,tierInt),portDestinationNumberInt);
                        }
                    }
                    else{
                        if(reefer){
                            matrixStackTierAftContainers[stackInt][tierInt]="cap20reeferContainer";
                            destinationPortsMapLocal.put(new BayCell(bayFromLocationValue,stackInt,tierInt),portDestinationNumberInt);
                        }
                        else{
                            matrixStackTierAftContainers[stackInt][tierInt]="cap20Container";
                            destinationPortsMapLocal.put(new BayCell(bayFromLocationValue,stackInt,tierInt),portDestinationNumberInt);
                        }
                    }    

                        session.setAttribute("matrixAFT"+stringBayValue,matrixStackTierAftContainers);
                        session.setAttribute("matrixFORE"+stringBayValue,matrixStackTierForeContainers);
                        session.setAttribute("destinationPortsMap"+stringBayValue,destinationPortsMapLocal);
                }
                container_manager.addContainer(bayFromLocationValue,stackDouble,tierDouble,container_cap,container_weight,reefer,portDestinationNumberInt);
            }
           
            coloursMap = new HashMap<Integer,Integer>();
            int iColour=1;
            List<Integer> ports = container_manager.extractDestinationPorts();
            ports.toString();
            for(Integer p:ports){
                coloursMap.put(p, iColour);
                iColour++;
            }
            
            parsingContainers=true;
        }
    
    }
    
    
    private String[][] design_bay_Aft(double bay){
        
         
        List<Double> stackNumberforBay = stack_manager.findStackNumberForBay(bay);
        
        String[][] matrixStackTierAft = new String[stackNumberforBay.size()][nb_layout_tiers];
        
        //Initialization matrix Stack AFT
        for(int l=0;l<stackNumberforBay.size();l++){
            for(int h=0;h<nb_layout_tiers;h++){
                matrixStackTierAft[l][h]="cap0";
            }
        }
        List<Cell> cellForEachStack;
        int numberStackInMatrix=0;
        int tierInt;
        for(Double stNumber:stackNumberforBay){
            
            cellForEachStack= cell_manager.findCellsForStack(stNumber, bay);
            
            for(Cell c:cellForEachStack){
               tierInt = (int) c.getTier()-1;
               if(c.getM_reeferAft()==1.0){
                    if(c.getM_cap40()==1.0)
                        matrixStackTierAft[numberStackInMatrix][tierInt]= "cap40reefer";
                    else
                        matrixStackTierAft[numberStackInMatrix][tierInt]= "cap20reefer";
               }
               else{
                    if(c.getM_cap40()==1.0)
                        matrixStackTierAft[numberStackInMatrix][tierInt]= "cap40";
                    else
                        matrixStackTierAft[numberStackInMatrix][tierInt]= "cap20";
               } 
           }
            numberStackInMatrix++;
        }
       //********************************************************************************
        
       //Number of real tiers for  AFT
       boolean okOnAft=false;
       
       tierOkOnAft=0;
       tierKOOnAft=0;
       
       for(int z=0;z < hatch_tier_idx ;z++){
            okOnAft=false;
            
            for(int k=0;k< matrixStackTierAft.length;k++){
                if(matrixStackTierAft[k][z].equals("cap40") || matrixStackTierAft[k][z].equals("cap20") || matrixStackTierAft[k][z].equals("cap40reefer") || matrixStackTierAft[k][z].equals("cap20reefer")) okOnAft=true; 
            }
            if(okOnAft==true)tierOkOnAft++;
            else tierKOOnAft++;
            if(tierOkOnAft==1)startTierIntAft=z;
            
       }
       
       //Number of real tiers for BELOW DECK AFT
       boolean okBelowAft=false;
       tierOkBelowAft=0;
       tierKOBelowAft=0;
       
       for(int z=hatch_tier_idx;z < nb_layout_tiers ;z++){
            okBelowAft=false;
            for(int k=0;k< matrixStackTierAft.length;k++){
                if(matrixStackTierAft[k][z].equals("cap20") || matrixStackTierAft[k][z].equals("cap40") || matrixStackTierAft[k][z].equals("cap40reefer") || matrixStackTierAft[k][z].equals("cap20reefer"))
                    okBelowAft=true;
            }
            if(okBelowAft==true)tierOkBelowAft++;
            else tierKOBelowAft++;
       }
       //******************************************************************************** 
       return matrixStackTierAft;
     }
    
      
   private String[][] design_bay_Fore(double bay){
       
        List<Double> stackNumberforBay = stack_manager.findStackNumberForBay(bay);
        
        String[][] matrixStackTierFore = new String[stackNumberforBay.size()][nb_layout_tiers];
        
        //Initialization matrix Stack FORE
        for(int l=0;l<stackNumberforBay.size();l++){
            for(int h=0;h<nb_layout_tiers;h++){
                matrixStackTierFore[l][h]="cap0";
            }
        }
        List<Cell> cellForEachStack;
        int numberStackInMatrix=0;
        int tierInt;
        for(Double stNumber:stackNumberforBay){
            
            cellForEachStack= cell_manager.findCellsForStack(stNumber, bay);
            
            for(Cell c:cellForEachStack){
               tierInt = (int) c.getTier()-1;
               if(c.getM_reeferFore()==1.0){
                    if(c.getM_cap40()==1.0)
                        matrixStackTierFore[numberStackInMatrix][tierInt]= "cap40reefer";
                    else
                        matrixStackTierFore[numberStackInMatrix][tierInt]= "cap20reefer";
               }
               else{
                    if(c.getM_cap40()==1.0)
                        matrixStackTierFore[numberStackInMatrix][tierInt]= "cap40";
                    else
                        matrixStackTierFore[numberStackInMatrix][tierInt]= "cap20";
               } 
           }
          numberStackInMatrix++;
        }
        //********************************************************************************
        
       //Number of real tiers for FORE ON
       boolean okOnFore=false;
       tierOkOnFore=0;
       tierKOOnFore=0;
       
       for(int z=0;z < hatch_tier_idx ;z++){
            okOnFore=false;
            for(int k=0;k< matrixStackTierFore.length;k++){
                if(matrixStackTierFore[k][z].equals("cap40") || matrixStackTierFore[k][z].equals("cap20") || matrixStackTierFore[k][z].equals("cap40reefer") || matrixStackTierFore[k][z].equals("cap20reefer")) okOnFore=true;
            }
            if(okOnFore==true)tierOkOnFore++;
            else tierKOOnFore++;
            if(tierOkOnFore==1)startTierIntFore=z;
       }
    
       //Number of real tiers for FORE BELOW 
       boolean okBelowFore=false;
       tierOkBelowFore=0;
       tierKOBelowFore=0;
       for(int z=hatch_tier_idx;z < nb_layout_tiers ;z++){
            okBelowFore=false;
            for(int k=0;k< matrixStackTierFore.length;k++){
                if(matrixStackTierFore[k][z].equals("cap20") || matrixStackTierFore[k][z].equals("cap40") || matrixStackTierFore[k][z].equals("cap40reefer") || matrixStackTierFore[k][z].equals("cap20reefer"))
                    okBelowFore=true;
            }
            if(okBelowFore==true)tierOkBelowFore++;
            else tierKOBelowFore++;
       }
       //******************************************************************************** 
       return matrixStackTierFore;
      }
       
  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
