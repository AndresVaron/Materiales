import {Component, OnInit} from '@angular/core';
import {SuffragiumService} from '../../suffragium.service';


@Component({
    selector: 'app-mapa-departamentos',
    templateUrl: './mapa-departamentos.component.html',
    styleUrls: ['./mapa-departamentos.component.css']
})
export class MapaDepartamentosComponent implements OnInit {

    constructor(private suffragiumService: SuffragiumService) {}
    lat: string;
    lng: string;
    corriendo:boolean;
    markers: marker[];
    mapStyles = [
        {
            "featureType": "poi",
            "elementType": "labels",
            "stylers": [
                {
                    "visibility": "off"
                }
            ]
        }
    ];

    getCols(): any {
        if (document.body.offsetWidth > 1080) {
            return 'col-sm-2';
        }
        return 'col-sm-1';
    }
    getCol(): any {
        if (document.body.offsetWidth > 1080) {
            return 'col-sm-8';
        }
        return 'col-sm-10';
    }

    /**
    * Cancels the creation of the new book
    * Redirects to the books' list page
    */
    parar(): void {
        this.corriendo = false;
        this.suffragiumService.toggle(false).subscribe(ok => {

        });
    }

    empezar(): void {
        this.corriendo = true;
        this.suffragiumService.toggle(true).subscribe(ok => {

        });
    }
    

    ngAfterViewInit(): void {
        this.timer = setInterval(() => {
            this.suffragiumService.getPings().subscribe(pings => {
            this.markers = [];
            for (let i = 0; i < pings.length; i++) {
                let ping = (<{hora: string, latitud: string, longitud: string,tipo: number}[]> pings)[i];
               let str = "";
                if(ping.tipo==1){
                    str = "https://i.imgur.com/aLl2ia7.png";
                }
                else{
                    str ="https://i.imgur.com/IwOK4ZZ.png";
                }          
                let marker: marker = {lat: Number(ping.latitud), lng: Number(ping.longitud), label: ("" + ping.tipo), lugar: ping.hora,icon:str};
                this.markers.push(marker);
            }
        });
        }, 5000);
    }
    timer: any;


    ngOnInit() {
        this.suffragiumService.getPrendido().subscribe(prendido => {
            if(prendido!=null)
            {
                this.corriendo = prendido.prendido;
                console.log(this.corriendo);
            }
        });
        this.suffragiumService.getPings().subscribe(pings => {
            this.markers = [];
            for (let i = 0; i < pings.length; i++) {
                let ping = (<{hora: string, latitud: string, longitud: string,tipo: number}[]> pings)[i];
                let str = "";
                if(ping.tipo==1){
                    str = "https://i.imgur.com/aLl2ia7.png";
                }
                else{
                    str ="https://i.imgur.com/IwOK4ZZ.png";
                }          
                let marker: marker = {lat: Number(ping.latitud), lng: Number(ping.longitud), label: ("" + ping.tipo), lugar: ping.hora,icon:str};
                this.markers.push(marker);
            }
        });
    }
    
    ngOnDestroy(): void {
        clearInterval(this.timer);
    }

}
interface marker {
    lat: number;
    lng: number;
    label?: string;
    lugar: string;
    icon:string;
}
