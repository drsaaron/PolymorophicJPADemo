# PolymorophicJPADemo

Demonstrate the use of polymorphism and inheritence in both JPA and jackson serialization.  This system defines a simple `Transaction` class that will have details of different types.  this particular implementation uses child tables and joins to provide the polymorphism, though other solutions in JPA are possible and perhaps more efficient.