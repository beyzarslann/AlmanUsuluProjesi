import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-beyza',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
  toplamHarcamaHesapla: boolean = false;
  toplamHesap: any;
  kullaniciMiktarAlani: boolean = true;
  kullanicilarAlani: boolean = false;
  harcamalar: any[] = [];
  toplamHarcama: any[] = [];
  ortalamaHarcama: number = 0;
  borclarHesabi: any[] = [];
  toplamHesapDoviz: any;

  private baseUrl = 'http://localhost:8081';

  title = 'Kullanıcı Sayısı Alma Ekranı';

  kullaniciMiktarForm = new FormGroup({
  sayi: new FormControl(2, [Validators.required, Validators.min(1), Validators.max(5)]),
  });

  kullanicilarForm = new FormGroup({
    kullanicilar: this._formBuilder.array([])
  });



  constructor(private _formBuilder: FormBuilder, private http: HttpClient) { }


  kaydet() {
    for (let index = 0; index < this.kullanicilar.length; index++) {

      const credentials = { kullaniciAdi: this.kullanicilar.controls[index].value.isim, kullaniciHarcama: this.kullanicilar.controls[index].value.harcama };

      this.http.post<any>(this.baseUrl + '/debts/save', credentials).subscribe(
        response => {
          console.log('Veri kaydedildi:', response);
        },
        error => {
          console.error('Veri kaydedilemedi:', error);
        }
      );
    }
  }

  harcamaHesap() {
    const harcamalar = [];

    for (let index = 0; index < this.kullanicilar.length; index++) {
      const credentials = {
        kullaniciAdi: this.kullanicilar.controls[index].value.isim,
        kullaniciHarcama: this.kullanicilar.controls[index].value.harcama
      };

      harcamalar.push(credentials);
    }

    this.http.post<any[]>(this.baseUrl + '/debts/harcamaHesap', harcamalar).subscribe(
      response => {
        this.borclarHesabi = response;
        this.toplamHarcamaHesapla = true;
      },
      error => {
        console.error('Borç hesaplanamadı:', error);
      }
    );

    this.http.post<any>(this.baseUrl + '/debts/toplamveOrtalamaHarcama', harcamalar).subscribe(
      response => {
        this.toplamHarcama = response.toplamHarcama;
        this.ortalamaHarcama = response.ortalamaHarcama;
        this.toplamHesapDoviz = response.toplamHesapDoviz;
      },
      error => {
        console.error('Toplam ve Ortalama Harcama Hesaplanamadı:', error);
      }
    );
  }

  ngOnInit(): void { }

  kullaniciMiktarFormGonder() {
    console.log("kullaniciMiktarFormGonder " + this.kullaniciMiktarForm.get('sayi')?.value);

    if (this.kullaniciMiktarForm.invalid) {
      alert("Hata ver");
      return;
    }

    this.kullaniciMiktarAlani = false;
    this.kullanicilarAlani = true;

    let toplam: number = this.kullaniciMiktarForm.get('sayi')?.value || 0;

    for (let index = 0; index < toplam; index++) {
      this.kullaniciEkle();
    }
  }

  geriDon() {
    this.kullanicilarAlani = false;
    this.kullaniciMiktarAlani = true;
    while (this.kullanicilar.length !== 0) {
      this.kullanicilar.removeAt(0)
    }
  }

  get kullanicilar() {
    return this.kullanicilarForm.controls["kullanicilar"] as FormArray;
  }

  kullaniciEkle() {

    const yeniKullanici = {
      isim: '',
      harcama: ''
    };

    this.kullanicilar.push(this._formBuilder.group({
      isim: ['', Validators.required],
      harcama: ['', Validators.required]
    }));
  }

  kullaniciSil(index: number) {
    this.kullanicilar.removeAt(index);
  }
}