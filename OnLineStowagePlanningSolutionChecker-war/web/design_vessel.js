function designBay1(){
   
   
var vessel = document.getElementById("vessel");

// move element "on top of" all others within the same grouping
//vessel.parentNode.appendChild(vessel); 


// move element "underneath" all others within the same grouping
//vessel.parentNode.insertBefore(vessel,vessel.parentNode.firstChild);

// move element "on top of" all others in the entire document
//vessel.ownerSVGElement.appendChild(vessel); 

// move element "underneath" all others in the entire document
//vessel.ownerSVGElement.appendChild(vessel,vessel.ownerSVGElement.firstChild); 
//vessel.toBack();
var paper = new Raphael(document.getElementById('bay1'), 60, 60);  
var circle = paper.circle(50, 50, 80); 

circle.attr (
        {
            fill: '#F00'
        }
    );
circle.attr("stroke", "#f1f1f1");
circle.attr("stroke-width", 2);
//circle.toFront();
}



