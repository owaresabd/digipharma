
var geocoder;
var all_overlays = [];
var polygonArray = [];
var afterInfo = [];
function initMap() {
	var map = new google.maps.Map(
		    document.getElementById("map-canvas"), {
		      center: new google.maps.LatLng(23.8403, 90.4222),
		      zoom: 13,
		      mapTypeId: google.maps.MapTypeId.ROADMAP
		    });
		  var polyOptions = {
		    strokeWeight: 0,
		    fillOpacity: 0.45,
		    editable: true,
		    fillColor: '#FF1493'
		  };
		  var selectedShape;

		  var drawingManager = new google.maps.drawing.DrawingManager({
			    drawingMode: google.maps.drawing.OverlayType.MARKER,
			    drawingControl: true,
			    drawingControlOptions: {
			      position: google.maps.ControlPosition.TOP_CENTER,
			      draggable: true,
			      drawingModes: [ 'polygon', 'polyline']
			    },
			    polygonOptions: {
			    	draggable: true, // turn off if it gets annoying
			        editable: true,
			        strokeColor: '#FF0000',
			        strokeOpacity: 0.8,
			        strokeWeight: 3,
			        fillColor: '#FF0000',
			        fillOpacity: 0.35
			      }
			  });

		 
  google.maps.event.addListener(drawingManager, 'polygoncomplete', function(polygon) {
	   // document.getElementById('info').innerHTML += "polygon points:" ;
	    for (var i = 0; i < polygon.getPath().getLength(); i++) {
	     var infoVal= document.getElementById('info').value +=  "" + polygon.getPath().getAt(i).toUrlValue(6) + "- "; //"(" + polygon.getPath().getAt(i).toUrlValue(6)+ ") - ";
	     var latitudesVal= document.getElementById('latitudes').value +=  "" + polygon.getPath().getAt(i).lat(6) + ", "; //"(" + polygon.getPath().getAt(i).toUrlValue(6)+ ") - ";
	     var longitudesVal= document.getElementById('longitudes').value +=  "" + polygon.getPath().getAt(i).lng(6) + ", "; //"(" + polygon.getPath().getAt(i).toUrlValue(6)+ ") - ";

	      $('#resetPolygon').show();
	      $('#submit-button').show();
	      
	    }	  	
	    $('#info').val(infoVal.trim().substring(0, infoVal.trim().length - 1));
	    $('#latLng').val(infoVal.trim().substring(0, infoVal.trim().length - 1));
	    $('#latitudes').val(latitudesVal.trim().substring(0, latitudesVal.trim().length - 1));
	    $('#longitudes').val(longitudesVal.trim().substring(0, longitudesVal.trim().length - 1));
	    
	    polygonArray.push(polygon);
	    drawingManager.setDrawingMode(null);
	    drawingManager.setOptions({
	      drawingControl: false
	    });
	    function getPolygonCoords() {
	  	  var len = polygon.getPath().getLength();
	  	  var htmlStr = "";
	  	 var latitudesVal = "";
	  	 var longitudesVal = "";
	  	  for (var i = 0; i < len; i++) {
	  	    htmlStr +=  "" + polygon.getPath().getAt(i).toUrlValue(6); +"- "; //"(" + polygon.getPath().getAt(i).toUrlValue(6) + ") - ";
	  	     latitudesVal +=  "" + polygon.getPath().getAt(i).lat(6);+", "; //"(" + polygon.getPath().getAt(i).toUrlValue(6)+ ") - ";
		     longitudesVal +=  "" + polygon.getPath().getAt(i).lng(6);+", "; //"(" + polygon.getPath().getAt(i).toUrlValue(6)+ ") - ";
		  	$('#info').val(htmlStr.trim().substring(0, htmlStr.trim().length - 1));
		  	$('#latLng').val(htmlStr.trim().substring(0, htmlStr.trim().length - 1));
		    $('#latitudes').val(latitudesVal.trim().substring(0, latitudesVal.trim().length - 1));
		    $('#longitudes').val(longitudesVal.trim().substring(0, longitudesVal.trim().length - 1));
	  	 
	  	  }
	 
	  	}
	    //google.maps.event.addListener(myPolygon, "dragend", getPolygonCoords);
	    google.maps.event.addListener(polygon.getPath(), "insert_at", getPolygonCoords);
	    //google.maps.event.addListener(myPolygon.getPath(), "remove_at", getPolygonCoords);
	    google.maps.event.addListener(polygon.getPath(), "set_at", getPolygonCoords);
	  });
  google.maps.event.addListener(drawingManager, 'polygoncomplete', function(event) {
      google.maps.event.addListener(event, 'bounds_changed', function(){
          if (event.type == google.maps.drawing.OverlayType.POLYGON) {
              var bounds = event.getBounds(); // Here are the new bounds after moving the rectangle around or resizing it.
            
            // Rest of the code here.
          }
      });
  });
/*  function splitInto(str, len) {
      var regex = new RegExp('.{' + len + '}|.{1,' + Number(len-1) + '}', 'g');
      return str.match(regex );
  }*/
  
  $('#enablePolygon').click(function() {
	    drawingManager.setMap(map);
	    drawingManager.setDrawingMode(google.maps.drawing.OverlayType.POLYGON);
	  
	    drawingManager.setOptions({
	      drawingControl: false
	    });
	  });

	  $('#resetPolygon').click(function() {
	    if (selectedShape) {
	      selectedShape.setMap(null);
	      document.getElementById('info').value ="";
	      $("#longitudes").val("");
	      $("#latitudes").val("");
	    }
	    drawingManager.setMap(null);
	    $('#showonPolygon').hide();
	    $('#resetPolygon').hide();
	    $('#submit-button').hide();
	  });
	  //google.maps.event.addListener(drawingManager, 'polygoncomplete', function(polygon) {
	    //  var area = google.maps.geometry.spherical.computeArea(selectedShape.getPath());
	    //  $('#areaPolygon').html(area.toFixed(2)+' Sq meters');
	    //$('#resetPolygon').show();
	 // });

	  function clearSelection() {
	    if (selectedShape) {
	      selectedShape.setEditable(false);
	      selectedShape = null;
	    }
	  }


	  function setSelection(shape) {
	    clearSelection();
	    selectedShape = shape;
	    shape.setEditable(true);
	  }

	  google.maps.event.addListener(drawingManager, 'overlaycomplete', function(e) {
	    all_overlays.push(e);
	    if (e.type != google.maps.drawing.OverlayType.MARKER) {
	      // Switch back to non-drawing mode after drawing a shape.
	      drawingManager.setDrawingMode(null);

	      // Add an event listener that selects the newly-drawn shape when the user
	      // mouses down on it.
	      var newShape = e.overlay;
	      newShape.type = e.type;
	      google.maps.event.addListener(newShape, 'click', function() {
	        setSelection(newShape);
	      });
	      setSelection(newShape);
	    }
	  });
	  
	  
	}


	//google.maps.event.addDomListener(window, "load", initialize);
	