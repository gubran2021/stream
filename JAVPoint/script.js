jwplayer("container").setup({

		    controls: true,
		    displaytitle: true,
		    fullscreen: "false",
		    primary: 'html5',
		    stretching: "exactfit",
		    autostart: true,

		    skin: {
		    	name: 'Netflix',
		    },

		    //sharing: {
		    	//sites: ["reddit","facebook","twitter"]
		    //},
		     
		    captions: {
		        color: '#FFF',
		         fontSize: 24,
				 backgroundOpacity: 0,
		         edgeStyle: 'uniform' 
		    },

		  playlist: [
        ,
        //Begin Movies Truyen Ky Ly Tieu Long Tap 1
       {        
		    title: "مجموعة افلام",
		    description: "Truyền",
		    image: "",
		    sources: [{
		    file:"https://s01.drop.download:182/d/ghc4zebc4wtidai4x2u5lfij2y47oyc7wqoawcaoqwlykygrg7u4lvzu3d42rzvkbfzayepghhh/Tom.And.Jerry.2021.720p.WEBRip.x264.AAC-[YTS.MX].mp4",
		      label: '4K',
		      'type': 'mp4',
		      primary: 'html5',

		    },{
		   
		    }],
		    captions: [{
		      file: "https://mycinema.thsite.top/movies/Furiosa.A.Mad.Max.Saga.mp4/Furiosa.A.Mad.Max.Saga.srt",
		      label: 'Arabic',
		      kind: "captions",
		      "default": true,
		     
		       	}
		    
			],
		    
	
		    
		  }//end of movies
      
      ]
		});
jwplayer("container").setCaptions({
  "back": true,
  "edgeStyle": "uniform",
  "fontSize": 17,
   "fontOpacity": 100,
  "fontScale": 0.00,
  "windowOpacity": 0,
  "color": "#ffffff"
});