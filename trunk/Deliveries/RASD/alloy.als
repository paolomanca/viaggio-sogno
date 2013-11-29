/* Alloy model for the TravelDream Requirements And Specification Document. See the document [ chapater 5.1 - Alloy ] for specifications about the model represented here.*/

/*Enum representing the status of a package selected by a customer*/
enum PackageStatus {pending, reserved, paid}
/*Enum representing the state of an entity in the .*/
enum State {initial, afterOp}

/*Entities*/

/*Actors. The unregistered user is not present, since we concluded that it cannot modify the system, so it was not essential to model it.*/
sig Employee{
	ID : one Int,
	products: set Product,
	packages: set Package,
	state : one State
}
{
	ID > 0
	all p:products | p.state = state
	all p:packages | p.state = state
}

sig Customer{
	ID : one Int,
	finalPackages: set FinalPackage,
	state : one State
}
{
	ID > 0
	all f:finalPackages | f.state = state
}

/*Entities conforming with definitions on RASD.*/
sig FinalPackage{
	ID : one Int,
	products: set Product,
	finalProducts: set FinalProduct,
	status : one PackageStatus,
	state : one State
}
{
	ID > 0
	all p:products | p.state = state
	all fP:finalProducts | fP.state = state
}

sig Package{
	ID : one Int,
	products : some Product,
	state : one State
}
{
	ID > 0
	all p:products | p.state = state
}

sig FinalProduct{
	ID : one Int,
	state : one State
}
{
	ID > 0
}

sig Product{
	ID : one Int,
	state : one State
}
{
	ID > 0
}

/*Facts*/

/*Every state should be consistent in itself. Every entity is uniquely identified by an ID and its type. So no duplicate entities are admitted in the same state (we will constrain the uniqueness across states depending on the operation performed.*/
fact noDuplicateIDsInSameState {
	all c1,c2:Customer | (c1!=c2 and c1.state=c2.state) => c1.ID != c2.ID
	all c1,c2:Employee | (c1!=c2 and c1.state=c2.state) => c1.ID != c2.ID
	all c1,c2:FinalPackage | (c1!=c2 and c1.state=c2.state) => c1.ID != c2.ID
	all c1,c2:Package | (c1!=c2 and c1.state=c2.state) => c1.ID != c2.ID
	all c1,c2:FinalProduct | (c1!=c2 and c1.state=c2.state) => c1.ID != c2.ID
	all c1,c2:Product | (c1!=c2 and c1.state=c2.state) => c1.ID != c2.ID
}

/*No final packages can be shared by customers*/
fact noDuplicateFinalPackages {
	all c1,c2:Customer, fP:FinalPackage | c1!=c2 and c1.state = c2.state and c2.state = fP.state => (fP in c1.finalPackages => fP not in c2.finalPackages) or (fP in c2.finalPackages => fP not in c1.finalPackages)
}

/*No final products can be shared by final packages*/
fact noDuplicateFinalProducts {
	all c1,c2:FinalPackage, fP:FinalProduct | c1!=c2 and c1.state = c2.state and c2.state = fP.state => (fP in c1.finalProducts => fP not in c2.finalProducts) or (fP in c2.finalProducts => fP not in c1.finalProducts)
}

/*	No final package can exist without a customer having created it.
	No product can exist without an employee having created it.
	No package can exist without an employee having created it.
	No final product can exist without belonging to a final package. */
fact noOrphanedItems {
	no f:FinalPackage | all c:Customer | f not in c.finalPackages
	no f:Product | all c:Employee | f not in c.products
	no f:Package | all c:Employee | f not in c.packages
	no f:FinalProduct | all c:FinalPackage | f not in c.finalProducts
}

/*We restrain the models to comply with a static view (only one state) or only one of the possibile operations*/
fact admittableOperations{
	static or noChange or reserveFinalPackage or payFinalPackage or createFinalPackage
}

/*Paid or reserved final packages should have at least a final product and no products. No final package can be empty*/
fact finalPackagesConsistency{
	all f:FinalPackage | (f.status = reserved or f.status = paid) => (f.products = none and #f.finalProducts>0)
	all f:FinalPackage | #f.products >0 or #f.finalProducts >0
}

/*Predicates*/

/*True iff two customers entities are the same customer in two different states and have not removed or added any final packages owned during the operation*/
pred noAddedOrRemovedPackagesFromCustomer(c1,c2:Customer){
	c1.state!=c2.state
	c1.ID = c2.ID
	all f1:FinalPackage | f1 in c1.finalPackages => (one f2:FinalPackage | f2 in c2.finalPackages and f1.ID = f2.ID)
	all f1:FinalPackage | f1 in c2.finalPackages => (one f2:FinalPackage | f2 in c1.finalPackages and f1.ID = f2.ID)
}

/*True iff two customers entities are the same customer in two different states (c1 before the operation and c2 after) and have not removed any final packages owned during the operation*/
pred noRemovedPackagesFromCustomer(c1,c2:Customer){
	c1.state = initial
	c2.state = afterOp
	c1.ID = c2.ID
	all f1:FinalPackage | f1 in c1.finalPackages => (one f2:FinalPackage | f2 in c2.finalPackages and f1.ID = f2.ID)
}

/*True iff two final packages entities are the same final package in two different states and have not removed or added any final products owned during the operation*/
pred noAddedOrRemovedFinalProductsFromFinalPackage(c1,c2:FinalPackage){
	c1.state!=c2.state
	c1.ID = c2.ID
	all f1:FinalProduct | f1 in c1.finalProducts => (one f2:FinalProduct | f2 in c2.finalProducts and f1.ID = f2.ID)
	all f1:FinalProduct | f1 in c2.finalProducts => (one f2:FinalProduct | f2 in c1.finalProducts and f1.ID = f2.ID)
}

/*True iff two final packages entities are the same final package in two different states and have not changed the status (paid, reserved or pending) during the operation*/
pred unmodifiedStatusOfFinalPackage(p1,p2:FinalPackage){
	p1.state!=p2.state
	p1.ID = p2.ID
	p1.status = p2.status
}

/*True iff two final packages entities are the same final package in two different states and have not removed or added any products owned during the operation*/
pred noAddedOrRemovedProductsFromFinalPackage(c1,c2:FinalPackage){
	c1.state!=c2.state
	c1.ID = c2.ID
	all f1:Product | f1 in c1.products => (one f2:Product | f2 in c2.products and f1.ID = f2.ID)
	all f1:Product | f1 in c2.products => (one f2:Product | f2 in c1.products and f1.ID = f2.ID)
}

/*True iff two employees entities are the same employee in two different states and have not removed or added any package owned during the operation*/
pred noAddedOrRemovedPackagesFromEmployee(c1,c2:Employee){
	c1.state!=c2.state
	c1.ID = c2.ID
	all f1:Package | f1 in c1.packages => (one f2:Package | f2 in c2.packages and f1.ID = f2.ID)
	all f1:Package | f1 in c2.packages => (one f2:Package | f2 in c1.packages and f1.ID = f2.ID)
	all f1:Product | f1 in c1.products => (one f2:Product | f2 in c2.products and f1.ID = f2.ID)
	all f1:Product | f1 in c2.products => (one f2:Product | f2 in c1.products and f1.ID = f2.ID)
}

/*True iff two packages entities are the same package in two different states and have not removed or added any products owned during the operation*/
pred noAddedOrRemovedProductsFromPackage(c1,c2:Package){
	c1.state!=c2.state
	c1.ID = c2.ID
	all f1:Product | f1 in c1.products => (one f2:Product | f2 in c2.products and f1.ID = f2.ID)
	all f1:Product | f1 in c2.products => (one f2:Product | f2 in c1.products and f1.ID = f2.ID)
}

/*True iff a customer has a representation in both states, and in the state after the operation he has reserved one and only one final package that he owned*/
pred reserveFinalPackage(c1:Customer, f1,f2:FinalPackage){
	f1 in c1.finalPackages
	f1.status = pending
	one c2:Customer | customerOperation[c1,c2] and f2 in c2.finalPackages and f2.status = reserved and f1.ID = f2.ID and sameFinalProducts[f1, f2]
}

/*True iff a customer in the state after the operation has reserved one final package (f2) that he owned (f1)*/
pred payFinalPackage(c1:Customer, f1,f2:FinalPackage){
	f1 in c1.finalPackages
	f1.status = reserved
	one c2:Customer | customerOperation[c1,c2] and f2 in c2.finalPackages and f2.status = paid and f1.ID = f2.ID and sameFinalProducts[f1, f2]
}

/*True iff a customer in the state after the operation has created (selected) one final package (f) that has the same products*/
pred createFinalPackage(c1:Customer, p:Package, f:FinalPackage){
	f.state = afterOp
	p.state = initial
	f.status = pending
	f.finalProducts = none
	all a:Product | a in p.products => (one b:Product | b in f.products and b.ID = a.ID)
	all a:Product | a in f.products => (one b:Product | b in p.products and b.ID = a.ID)
	one c2:Customer | customerOperation[c1,c2] and f in c2.finalPackages
}

/*True iff two final packages contain exactly the same final products*/
pred sameFinalProducts(c1,c2:FinalPackage){
	all f1:FinalProduct | f1 in c1.finalProducts => (one f2:FinalProduct | f2 in c2.finalProducts and f1.ID = f2.ID)
	all f1:FinalProduct | f1 in c2.finalProducts => (one f2:FinalProduct | f2 in c1.finalProducts and f1.ID = f2.ID)
}

/*True iff two final packages contain exactly the same products*/
pred sameProducts(c1,c2:FinalPackage){
	all f1:Product | f1 in c1.products => (one f2:Product | f2 in c2.products and f1.ID = f2.ID)
	all f1:Product | f1 in c2.products => (one f2:Product | f2 in c1.products and f1.ID = f2.ID)
}

/*True iff two customer entities represent the same customer across an operation (c1 before the operation, c2 after*/
pred customerOperation(c1,c2:Customer){
	c1.state = initial
	c2.state = afterOp
	c1.ID = c2.ID
}

/*Model subtypes*/
/*Only initial state allowed*/
pred static(){
	no c:Customer | c.state = afterOp
	no e:Employee | e.state = afterOp
}

/*No operation performed between states.*/
pred noChange{
	all c1:Customer | one c2:Customer | noAddedOrRemovedPackagesFromCustomer[c1,c2]
	all c1:Employee | one c2:Employee | noAddedOrRemovedPackagesFromEmployee[c1,c2]
	all c1:FinalPackage | one c2:FinalPackage | noAddedOrRemovedProductsFromFinalPackage[c1,c2]
	all c1:FinalPackage | one c2:FinalPackage | noAddedOrRemovedFinalProductsFromFinalPackage[c1,c2]
	all c1:FinalPackage | one c2:FinalPackage | unmodifiedStatusOfFinalPackage[c1,c2]
	all p1:Package | one p2:Package | noAddedOrRemovedProductsFromPackage[p1,p2]
}

/*Only one customer reserved only one of its final packages*/
pred reserveFinalPackage{
	one c1:Customer, f1,f2:FinalPackage | reserveFinalPackage[c1,f1,f2]
	all c1:Customer | one c2:Customer | noAddedOrRemovedPackagesFromCustomer[c1,c2]
	all c1:Employee | one c2:Employee | noAddedOrRemovedPackagesFromEmployee[c1,c2]
	all c1:FinalPackage | one c2:FinalPackage | noAddedOrRemovedProductsFromFinalPackage[c1,c2]
	all c1:FinalPackage | one c2:FinalPackage | noAddedOrRemovedFinalProductsFromFinalPackage[c1,c2]
	all f1:FinalPackage | one f2:FinalPackage | !(some c:Customer | reserveFinalPackage[c,f1,f2] or reserveFinalPackage[c,f2,f1]) => unmodifiedStatusOfFinalPackage[f1,f2]
	all p1:Package | one p2:Package | noAddedOrRemovedProductsFromPackage[p1,p2]
}

/*Only one customer payed (or bettere the payment was notified to the system) only one of its final packages*/
pred payFinalPackage{
	one c1:Customer, f1,f2:FinalPackage | payFinalPackage[c1,f1,f2]
	all c1:Customer | one c2:Customer | noAddedOrRemovedPackagesFromCustomer[c1,c2]
	all c1:Employee | one c2:Employee | noAddedOrRemovedPackagesFromEmployee[c1,c2]
	all c1:FinalPackage | one c2:FinalPackage | noAddedOrRemovedProductsFromFinalPackage[c1,c2]
	all c1:FinalPackage | one c2:FinalPackage | noAddedOrRemovedFinalProductsFromFinalPackage[c1,c2]
	all f1:FinalPackage | one f2:FinalPackage | !(some c:Customer | payFinalPackage[c,f1,f2] or payFinalPackage[c,f2,f1]) => unmodifiedStatusOfFinalPackage[f1,f2]
	all p1:Package | one p2:Package | noAddedOrRemovedProductsFromPackage[p1,p2]
}

/*Only one customer selected one of the basic packages to customize (creating a new final package)*/
pred createFinalPackage{
	one c1:Customer, p:Package, f:FinalPackage | createFinalPackage[c1,p,f]
	all c1:Customer | !(some p:Package, f:FinalPackage | createFinalPackage[c1,p,f]) and c1.state=initial => (one c2:Customer | noAddedOrRemovedPackagesFromCustomer[c1,c2])
	all c1:Customer | (some p:Package, f:FinalPackage | createFinalPackage[c1,p,f]) => (one c2:Customer | noRemovedPackagesFromCustomer[c1,c2] and #(c2.finalPackages)=plus[#(c1.finalPackages),1])
	#{c:Customer | c.state=initial}=#{c:Customer | c.state=afterOp}
	all c1:Employee | one c2:Employee | noAddedOrRemovedPackagesFromEmployee[c1,c2]
	all c1:FinalPackage | c1.state=initial => one c2:FinalPackage | noAddedOrRemovedProductsFromFinalPackage[c1,c2]
	all c1:FinalPackage | c1.state=initial => one c2:FinalPackage | noAddedOrRemovedFinalProductsFromFinalPackage[c1,c2]
	all f1:FinalPackage | f1.state=initial => (one f2:FinalPackage | unmodifiedStatusOfFinalPackage[f1,f2])
	all f1:FinalPackage | f1.state=afterOp => (one f2:FinalPackage | unmodifiedStatusOfFinalPackage[f1,f2]) or (one c1:Customer, p:Package | createFinalPackage[c1,p,f1])
	all p1:Package | one p2:Package | noAddedOrRemovedProductsFromPackage[p1,p2]
}

/*Return all of the pair of final packages entities in which, the first final package is in the initial state and the the second one is in the after operation state. Also they should represent the same final package and an operation must have not modified it.*/
fun identicalFinalPackages : set FinalPackage -> FinalPackage{
	{f1,f2:FinalPackage |	f1.state = initial and f2.state = afterOp and unmodifiedStatusOfFinalPackage[f1,f2]}
}

/*Assertions*/
/*No package can belong to multiple customers*/
assert noDuplicateFinalPackages {
	no f:FinalPackage | all c1,c2:Customer | c1!=c2 and f in c1.finalPackages and f in c2.finalPackages
}

check noDuplicateFinalPackages

/*Every operation acts upon one final package at most.*/
assert maximumOneOperationOnPackages {
	!static =>	(#identicalFinalPackages >= minus[(#{f:FinalPackage | f.state = initial}) ,1])
}

check maximumOneOperationOnPackages

/*Every basic package is not modifiable.*/
assert packagesUnmodifiable{
	no p1: Package | p1.state = initial and (some p2:Package | p2.state = afterOp and p1.ID=p2.ID and !(noAddedOrRemovedProductsFromPackage[p1,p2]))
}

check packagesUnmodifiable for 4

/*Predicates aiding in the visualization of a model subtype*/
pred showStatic(){
	static
	#Customer > 0
	#FinalPackage > 1
	#FinalProduct > 1
	#FinalProduct > 1
	#Product > 1
}

pred showDynamicNoChange{
	noChange
	#Customer > 0
	#FinalPackage > 0
}

pred showDynamicReserveFinalPackage{
	reserveFinalPackage
	#Customer > 0
	#FinalPackage > 0
}

pred showDynamicPayFinalPackage{
	payFinalPackage
	#Customer > 0
	#FinalPackage > 0
}

pred showDynamicCreateFinalPackage{
	createFinalPackage
	#Customer > 0
	#FinalPackage > 0
}

/*Run the aiding predicates*/
run showStatic for 4 but 4 FinalPackage, 4 Product, 1 Package
run showDynamicNoChange for 4 but 4 FinalPackage, exactly 6 Product, exactly 4 Package, 2 Employee
run showDynamicReserveFinalPackage for 4 but exactly 6 FinalPackage
run showDynamicPayFinalPackage for 4  but exactly 6 FinalPackage
run showDynamicCreateFinalPackage for 4  but exactly 5 FinalPackage, exactly 4 Package, exactly 10 Product
