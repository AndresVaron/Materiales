import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class SuffragiumService {

    /**
      * Constructor of the service
      * @param http The HttpClient - This is necessary in order to perform requests
      */
    constructor(private http: HttpClient) {}

    getPings(): Observable<Object[]> {
        return this.http.get<Object[]>("http://157.253.238.75:8080/materiales/api/ping/ultimos");
    }
    
    getPrendido(): Observable<Object[]> {
        return this.http.get<Object[]>("http://157.253.238.75:8080/materiales/api/maleta/prendido");
    }
    
    
    toggle(bol:boolean): Observable<Object[]> {
        if(bol==false){   
            return this.http.get<Object[]>("http://157.253.238.75:8080/materiales/api/maleta/apagar");   
        }else{
            return this.http.get<Object[]>("http://157.253.238.75:8080/materiales/api/maleta/prender");   
        }
    }
}
