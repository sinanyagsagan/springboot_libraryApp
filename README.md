# LibraryApp Spring Boot

Bu proje, Java Spring Boot kullanılarak geliştirilmiş bir kütüphane otomasyon uygulamasını içermektedir. Proje, aşağıdaki temel özelliklere sahiptir:

## Proje Özellikleri

- **ModelMapper:** Model sınıfları arasında veri transferini kolaylaştırmak ve nesneler arasındaki alanları haritalamak için ModelMapper kullanılmıştır.

- **Spring Annotations:** Spring framework'ünün sunduğu çeşitli anotasyonlar (örneğin, `@RestController`, `@Service`, `@Repository`, vb.) kullanılarak proje Spring tabanlı bir yapıya sahiptir.

- **Java Stream API:** Veri koleksiyonları üzerinde işlemleri sadeleştirmek ve performansı artırmak için Java Stream API kullanılmıştır.

- **Veritabanı Seçimi:** Proje, verileri saklamak için PostgreSQL veritabanını kullanmaktadır.

- **Yml Dosyası:** Veritabanı bağlantı bilgilerini içeren konfigürasyon dosyası `application.yml` ve `application-dev.yml` olarak seçilmiştir.

- **JPA (Java Persistence API):** Nesne ilişkilendirilmiş veri modellerini veritabanında depolamak ve erişmek için JPA kullanılmıştır.

- **Katmanlı Mimarisi:** Proje, katmanlı mimari prensiplerine uygun olarak tasarlanmıştır, böylece iş mantığı, veri erişimi ve sunum katmanları birbirinden ayrılmıştır.
  
## Tablolar

### 1. Customer

Müşteri bilgilerini içeren tablo.

| Alan         | Tür       | Açıklama                    |
|--------------|-----------|-----------------------------|
| customer_id  | LONG      | Müşteri kimlik numarası     |
| name         | STRING    | Müşteri adı                 |
| surname      | STRING    | Müşteri soyadı              |
| phone        | STRING    | Müşteri telefon             |
| address      | STRING    | Müşteri address             |



### 2. Book

Kitap bilgilerini içeren tablo.

| Alan         | Tür       | Açıklama                  |
|--------------|-----------|---------------------------|
| book_id      | LONG      | Kitap kimlik numarası     |
| name         | STRING    | Kitap adı                 |
| author       | STRING    | Kitap yazarı              |
| ISBN         | STRING    | Kitap numarası            |
| Language     | STRING    | Kitap dili                |
| volumeNo     | STRING    | Kitap cilt no             |
| publisher    | STRING    | Kitap yayıncı             |
| Category     | ENUM      | Kitap türü                |


### 3. BookStock

Kitap stoğunu içeren tablo. Book tablosuyla bağlantılıdır.

| Alan         | Tür        | Açıklama                               |
|--------------|------------|----------------------------------------|
| stock_id     | LONG       | Stok kimlik numarası                   |
| book_id      | BOOK       | Bağlı olduğu kitap referansı           |
| bookStock    | INT        | Mevcut stok miktarı                    |
| bookOffice   | ENUM       | Kitabın mevcut olduğu alan             |


### 4. Library

Kütüphane bilgilerini içeren tablo. Hem müşteri hem de kitap tablolarıyla bağlantılıdır.

| Alan          | Tür       | Açıklama                                |
|---------------|-----------|-----------------------------------------|
| library_id    | INT       | Kitap kayıt kimlik numarası             |
| customer_id   | INT       | Bağlı olduğu müşterinin kimlik numarası |
| book_id       | INT       | Bağlı olduğu kitabın kimlik numarası    |
| bookOffice    | ENUM       | Kitabın mevcut olduğu alan             |

