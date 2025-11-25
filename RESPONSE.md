RESPONSE DE NORA LÓPEZ MIGALLÓN

a. Identificación de principios SOLID y patrones de diseño aplicados: 

En primer lugar, podemos observar que en los módulos command y query del proyecto inicial se viola el Principio de Responsabilidad Única, pues no se da una separación clara entre la parte de datos y la de lógica. Para ello, cada una de sus clases iniciales se ha separado en dos: La que contiene únicamente los datos y la que contiene lo referente al handler. Por otra parte, las clases Customer, CustomerEntity, CustomersRepository y los DTOs sí que lo cumplen. 

Por otra parte, el hecho de que exista la interfaz CustomersRepository permite cumplir el principio Open/Closed, ya que implica añadir funcionalidades sin modificar la base. Esto también podría identificarse como cumplimiento del Principio de Sustitución de Liskov, ya que todas las implementaciones de dicha interfaz podrían sustituirla sin alterar los demás módulos. En esta interfaz también se puede observar el principio de segregación de interfaces, ya que no hay ninguna implementación que tenga métodos que no utilice o que carezcan de sentido según su responsabilidad. 

En cuanto a los patrones de diseño, se identifican el Builder, empleado para simplificar y hacer el código en caso de constructores con gran número de parámetros, como en las clases Customer, CreateCustomerCommand, GetCustomerDTO, PutCustomerDTO y CustomerEntity. Además, al tener Handlers que ejecutan las operaciones en los módulos command y query se  está haciendo uso de un patrón Command. 


b. Solución de la tarea: 

Como era necesario adaptar la implementación de los métodos dados en la parte del repository a los clientes almacenados en la base de datos, se ha empleado un patrón Builder para convertir indistintamente de la entidad a la clase, y viceversa. 



