# Activitat 06: Espera wait - Preguntes teòriques

## 1. Per què s'atura l'execució al cap d'un temps?

El programa s'atura perquè es produeix un bloqueig mutu. Això passa quan l'esdeveniment arriba a tenir 0 places disponibles i tots els assistents es troben que intenten cancel·lar, però com que ja tenen reserva, simplement la cancel·len i alliberen una plaça o que intenten fer una reserva però com no hi ha places, es queden bloquejats amb wait(). També intenten cancel·lar però com que no tenen cap reserva a la llista, el mètode cancelaReserva() no executa el notifyAll().

Els assistents que no tenen cap reserva intenten cancel·lar però com que no tenen res a cancel·lar, no executen el notifyAll() i ningú desperta als que estan esperant. Per tant, tots els threads es queden esperant indefinidament i el programa es para.


## 2. Què passaria si en lloc de una probabilitat de 50%-50% fora de 70%(ferReserva)-30%(cancel·lar)? I si foren al revés les probabilitats?

### Cas 1: 70% ferReserva - 30% cancelaReserva

Si modifiquem el codi del mètode run() així:
```java
@Override
public void run() {
    while (true) {
        if (random.nextInt(100) < 70) {
            esdeveniment.ferReserva(this);

        } else {
            esdeveniment.cancelaReserva(this);
        }
        
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

L'esdeveniment s'ompliria molt ràpidament perquè hi ha moltes més reserves que cancel·lacions. En pocs segons, totes les 5 places estarien ocupades i la majoria d'assistents es quedarien bloquejats esperant amb wait().

**Sortida:**
```
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-2 ha fet una reserva. Places disponibles: 3
Assistent-1 ha fet una reserva. Places disponibles: 2
Assistent-4 ha fet una reserva. Places disponibles: 1
Assistent-3 ha fet una reserva. Places disponibles: 0
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
Assistent-6 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 0
```
El sistema entraria en estat de bloqueig.


### Cas 2: 30% ferReserva - 70% cancelaReserva

Ara el codi seria:
```java
@Override
public void run() {
    while (true) {
        if (random.nextInt(100) < 30) {
            esdeveniment.ferReserva(this);
        } else {
            esdeveniment.cancelaReserva(this);
        }
        
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
Hi hauria molts més intents de cancel·lació que de reserva. Com que la probabilitat de cancel·lar és més alta, les places gairebé sempre estarien disponibles.

**Sortida:**
```
Assistent-0 ha fet una reserva. Places disponibles: 4
Assistent-2 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-5 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-1 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
Assistent-0 ha cancel·lat una reserva. Places disponibles: 5
Assistent-3 ha fet una reserva. Places disponibles: 4
Assistent-7 no ha pogut cancel·lar una reserva inexistent. Places disponibles: 4
```
Veuríem molts missatges de "no ha pogut cancel·lar una reserva inexistent".


## 3. Perquè creus que fa falta la llista i no valdria només amb una variable sencera de reserves?

Si només tingués una variable com int reservados un mateix assistent podria fer 10 reserves sense cap control, ocupant tot l'esdeveniment ell sol, qualsevol assistent podria cancel·lar sense haver fet cap reserva abans i també no podríem verificar si un assistent ja té una reserva abans de deixar-lo fer-ne una altra.
La llista és important perquè necessitem controlar qui té reserva, no només quantes reserves hi ha.