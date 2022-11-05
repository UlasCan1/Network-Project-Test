Feature: Urun Sepete Ekleme

  @AnasayfaURLKontrolu
  Scenario: Anasayfadaki URL in dogru geldiginin kontrol edilmesi
    * Cerez kabul pop up ini max "5" sn bekle.Gorunur olunca kabul et butonuna tikla
    * Anasayfadaki URL "https://www.network.com.tr/" seklinde mi kontrol edilir
    * Anasayfa arama alaninda "Ceket" aratilir
    * Anasayfa arama alaninda ENTER tusuna basilir
    * "6000" ms bekle
    * Daha Fazla Goster butonuna tiklanir ve sayfalarin acildigi kontrol edilir
    * Indirimli ilk urune hover edilir
    * Rastgele urun secimi yapilir
    * Sepete Git Tiklanilir
    * Devam Et Butonuna Tiklanir







