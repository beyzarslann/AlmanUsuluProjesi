import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Index } from '../model/Index';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IndexServiceService {

  private indexUrl : string;

  constructor(private http: HttpClient) {
    this.indexUrl = 'http://localhost:8081/index';
   }

   public findAll(): Observable<Index[]> {
    return this.http.get<Index[]>(this.indexUrl);
  }

  public save(index: Index) {
    return this.http.post<Index>(this.indexUrl, index);
  }
  
}
