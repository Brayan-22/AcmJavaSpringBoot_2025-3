# Inversión de Control (IoC)

## 🎬 Principio de Hollywood
> "No nos llames, nosotros te llamaremos"

---

## 📚 Idea Teórica

La Inversión de Control es un patrón de arquitectura donde el flujo de control se invierte:  
En lugar de que su codigo cree y controle los objetos, un **contenedor** o **framework** lo hace por ustedes.

➡️ **IoC** implementa el principio de **Inversión de Dependencias (DIP)**:  
Le cede a un contenedor la responsabilidad de crear y administrar las dependencias, olvidandonos de la creación manual y enfocandonos en la lógica de negocio

---

## 🔎 Tipos de IoC

- **Factory Pattern:** Un objeto fábrica crea instancias.
- **Service Locator:** Se consulta un "registro" central para obtener dependencias.
- **Dependency Injection (DI):** El contenedor entrega las dependencias directamente. (Nos centraremos en este)
- **Template Method / Event Driven:** El framework llama a tu código (por ejemplo, Servlets, callbacks).

---

## 🛠 Analogía

**Restaurante buffet:**

- **Sin IoC:** Vas la cocina, cocinas y te sirves.
- **Con IoC:** El chef (contenedor) te sirve en la mesa. Tú solo comes cuando te llaman.