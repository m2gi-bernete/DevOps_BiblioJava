package test.java;

import static org.junit.Assert.*;
import main.java.DataFrame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class TestDataFrame {

    DataFrame<String, String, Object> voidDF;
    DataFrame<String, String, Object> filledDF;

    DataFrame<String, String, Object> dataFrame;

    //Initialisation de deux DataFrame : un vide et un rempli
    @Before
    public void init() throws Exception{
        List<String> index1 = List.of();
        List<String> label1 = List.of();
        List<List<Object>> values1 = List.of();
        voidDF = new DataFrame<>(index1, label1, values1);


        List<String> index2 = List.of("ligne1", "ligne2", "ligne3");
        List<String> label2 = List.of("colonne1", "colonne2", "colonne3", "colonne4");
        List<List<Object>> values2 = List.of(
                List.of(1, 2, 3),
                List.of("I'm toto", "I'm tata", "I'm titi"),
                List.of('a', 'b', 'c'),
                List.of(true, false, true)
        );
        filledDF = new DataFrame<>(index2, label2, values2);
    }


            /* Tests sur la construction de DataFrame */


    //Ce test vérifie qu'une exception est levée si le nombre de valeurs dans une colonne n'est pas valide
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCreateDataFrameWithNoValueInColumn() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2");
        List<String> label2 = List.of("colonne1", "colonne2", "colonne3");
        List<List<Object>> values2 = List.of(
                List.of(1, 2),
                List.of(5, 6)
        );

        dataFrame = new DataFrame<>(index2, label2, values2);
    }

    //Ce test vérifie qu'une exception est levée si le nombre de valeurs dans une ligne n'est pas valide
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCreateDataFrameWithNoValueInLine() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2");
        List<String> label2 = List.of("colonne1", "colonne2", "colonne3");
        List<List<Object>> values2 = List.of(
                List.of(1, 2),
                List.of('a', 'b'),
                List.of("c")
        );

        dataFrame = new DataFrame<>(index2, label2, values2);
    }

    //Ce test vérifie qu'une exception est levée si le nombre de lignes est plus grand que le nombre d'indices
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCreateDataFrameWithMoreLineThanRequire() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2");
        List<String> label2 = List.of("colonne1", "colonne2", "colonne3");
        List<List<Object>> values2 = List.of(
                List.of(1, 2),
                List.of("I'm toto", "I'm tata", "I'm titi"),
                List.of(5.4, 6.2)
        );

        dataFrame = new DataFrame<>(index2, label2, values2);
    }

    //Ce test vérifie qu'une exception est levée si le nombre de colonnes est plus grand que le nombre de labels
    @Test (expected = IndexOutOfBoundsException.class)
    public void testCreateDataFrameWithMoreColumnThanRequire() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2");
        List<String> label2 = List.of("colonne1", "colonne2");
        List<List<Object>> values2 = List.of(
                List.of(1, 2),
                List.of(-3, -4),
                List.of("I'm toto", "I'm tata")
        );

        dataFrame = new DataFrame<>(index2, label2, values2);
    }

    /* This test verify that an exception is thrown when a column has elements of different types */
    @Test (expected = IllegalArgumentException.class)
    public void testCreateDataFrameWithDifferentTypesInColumn() throws Exception {
        List<String> index2 = List.of("ligne1", "ligne2");
        List<String> label2 = List.of("colonne1", "colonne2");
        List<List<Object>> values2 = List.of(
                List.of(1, 2),
                List.of("a", 'a')
        );
        dataFrame = new DataFrame<>(index2, label2, values2);
    }

    //Ce test vérifie que la création d'un Dataframe avec deux nom d'indice identique fonctionne
    // et que le premier est écrasé par le second
    @Test
    public void testCreateDataFrameWithSameIndex() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2", "ligne3", "ligne1");
        List<String> label2 = List.of("colonne1", "colonne2", "colonne3", "colonne4");
        List<List<Object>> values2 = List.of(
                List.of(9, 2, 3, 1),
                List.of("I'm truc", "I'm tata", "I'm titi", "I'm toto"),
                List.of('d', 'b', 'c', 'a'),
                List.of(false, false, true, true)
        );

        dataFrame = new DataFrame<>(index2, label2, values2);

        assertEquals(filledDF.toString(), dataFrame.toString());
    }

    //Ce test vérifie que la création d'un Dataframe avec deux nom de labels identique fonctionne
    // et que le premier est écrasé par le second
    @Test
    public void testCreateDataFrameWithSameLabel() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2", "ligne3");
        List<String> label2 = List.of("colonne1", "colonne2", "colonne2", "colonne3", "colonne4");
        List<List<Object>> values2 = List.of(
                List.of(1, 2, 3),
                List.of(1.2, 3.4, 5.6),
                List.of("I'm toto", "I'm tata", "I'm titi"),
                List.of('a', 'b', 'c'),
                List.of(true, false, true)
        );
        dataFrame = new DataFrame<>(index2, label2, values2);

        assertEquals(filledDF.toString(), dataFrame.toString());
    }


            /* Test sur les méthodes statistiques de DataFrame */

        /* Test sur filledDF */

    //Ce test vérifie que la fonction getMin(L label) donne la valeur minimale du dataFrame de la colonne label
    @Test
    public void testGetMinDataFrame(){
        Integer min = 1;
        assertEquals(filledDF.getMin("colonne1"), min);
    }

    //Ce test vérifie que la fonction getMin(L label) renvoie null car la colonne ne contient pas de nombre
    @Test
    public void testGetMinOnStringDataFrame(){
        assertNull(filledDF.getMin("colonne2"));
    }

    //Ce test vérifie que la fonction getMin(L label) renvoie null car la colonne ne contient pas de nombre
    @Test
    public void testGetMinOnCharDataFrame(){

        assertNull(filledDF.getMin("colonne3"));
    }

    //Ce test vérifie que la fonction getMin(L label) renvoie null car la colonne ne contient pas de nombre
    @Test
    public void testGetMinOnBoolDataFrame(){
        assertNull(filledDF.getMin("colonne4"));
    }

    //Ce test vérifie que la fonction getMax(L label) renvoie la valeur minimale même si elle est à la fin de la liste
    @Test
    public void testGetMinWithMinOnTheEndOfColumnDataFrame() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2", "ligne3");
        List<String> label2 = List.of("colonne1");
        List<List<Object>> values2 = List.of(
                List.of(1, 2, -666)
        );
        dataFrame = new DataFrame<>(index2, label2, values2);

        Integer min = -666;
        assertEquals(dataFrame.getMin("colonne1"), min);
    }


    //Ce test vérifie que la fonction getMax(L label) renvoie la valeur minimale même si elle est dupliquée
    @Test
    public void testGetMinWithDuplicateMinDataFrame() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2", "ligne3");
        List<String> label2 = List.of("colonne1");
        List<List<Object>> values2 = List.of(
                List.of(-666, -666, 3)
        );
        dataFrame = new DataFrame<>(index2, label2, values2);

        Integer min = -666;
        assertEquals(dataFrame.getMin("colonne1"), min);
    }

        //Test sur le max

    //Ce test vérifie que la fonction getMax(L label) donne la valeur maximale du dataFrame de la colonne label
    @Test
    public void testGetMaxDataFrame(){
        Integer max = 3;
        assertEquals(filledDF.getMax("colonne1"), max);
    }

    //Ce test vérifie que la fonction getMax(L label) renvoie null car la colonne ne contient pas de nombre
    @Test
    public void testGetMaxOnStringDataFrame(){
        assertNull(filledDF.getMax("colonne2"));
    }

    //Ce test vérifie que la fonction getMax(L label) renvoie null car la colonne ne contient pas de nombre
    @Test
    public void testGetMaxOnCharDataFrame(){

        assertNull(filledDF.getMax("colonne3"));
    }

    //Ce test vérifie que la fonction getMax(L label) renvoie null car la colonne ne contient pas de nombre
    @Test
    public void testGetMaxOnBoolDataFrame(){
        assertNull(filledDF.getMax("colonne4"));
    }

    //Ce test vérifie que la fonction getMax(L label) renvoie la valeur maximale même si elle est à la fin de la liste
    @Test
    public void testGetMaxWithMaxOnTheEndOfColumnDataFrame() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2", "ligne3");
        List<String> label2 = List.of("colonne1");
        List<List<Object>> values2 = List.of(
                List.of(1, 2, 666)
        );
        dataFrame = new DataFrame<>(index2, label2, values2);

        Integer max = 666;
        assertEquals(dataFrame.getMax("colonne1"), max);
    }

    //Ce test vérifie que la fonction getMax(L label) renvoie la valeur maximale même si elle est dupliquée
    @Test
    public void testGetMaxWithDuplicateMaxDataFrame() throws Exception {

        List<String> index2 = List.of("ligne1", "ligne2", "ligne3");
        List<String> label2 = List.of("colonne1");
        List<List<Object>> values2 = List.of(
                List.of(666, 666, 3)
        );
        dataFrame = new DataFrame<>(index2, label2, values2);

        Integer max = 666;
        assertEquals(dataFrame.getMax("colonne1"), max);
    }


        //Tests sur les autres méthodes statistiques


    //Ce test vérifie que la fonction getAverage(L label) donne la valeur moyenne du dataFrame de la colonne label
    @Test
    public void testGetAverageDataFrame(){
        double average = 2;
        assertEquals(filledDF.getAverage("colonne1").doubleValue(), average, 0.0001);
    }

    //Ce test vérifie que la fonction getCount(L label) donne le nombre d'entrée non null du dataFrame de la colonne label
    @Test
    public void testGetCountDataFrame(){
        int count = 3;
        assertEquals(filledDF.getCount("colonne1"), count);
    }

    //Ce test vérifie que la fonction getSum(L label) calcule la somme des entrées non null du dataFrame de la colonne label
    @Test
    public void testGetSumDataFrame(){
        double sum = 6;
        assertEquals(filledDF.getSum("colonne1").doubleValue(), sum, 0.0001);
    }

    //Ce test vérifie que la fonction getAbsolute(L label) calcule la valeur absolue de somme des entrées non null
    // du dataFrame de la colonne label
    @Test
    public void testAbsoluteMinDataFrame(){
        double absolute = 6;
        assertEquals(filledDF.getAbsolute("colonne1").doubleValue(), absolute, 0.0001);
    }

    //Ce test vérifie que la fonction getProduct(L label) calcule le produit des entrées non null du dataFrame de la colonne label
    @Test
    public void testGetProductDataFrame(){
        double product = 6;
        assertEquals(filledDF.getProduct("colonne1").doubleValue(), product, 0.0001);
    }

        //test sur des colonnes non valide

    //Ce test vérifie que la fonction getAverage(L label) sur une mauvaise colonne donne null
    @Test
    public void testGetAverageOnWrongColumnDataFrame(){
        assertNull(filledDF.getAverage("colonneA"));
    }

    //Ce test vérifie que la fonction getCount(L label) sur une mauvaise colonne donne -1
    @Test
    public void testGetCountOnWrongColumnDataFrame(){
        int count = -1;
        assertEquals(filledDF.getCount("colonneA"), count);
    }

    //Ce test vérifie que la fonction getSum(L label) sur une mauvaise colonne donne null
    @Test
    public void testGetSumOnWrongColumnDataFrame(){
        assertNull(filledDF.getSum("colonneA"));
    }

    //Ce test vérifie que la fonction getAbsolute(L label) sur une mauvaise colonne donne null
    // du dataFrame de la colonne label
    @Test
    public void testAbsoluteMinOnWrongColumnDataFrame(){
        assertNull(filledDF.getAbsolute("colonneA"));
    }

    //Ce test vérifie que la fonction getProduct(L label) sur une mauvaise colonne donne null
    @Test
    public void testGetProductOnWrongColumnDataFrame(){
        assertNull(filledDF.getProduct("colonneA"));
    }

        //Test sur des String

    //Ce test vérifie que la fonction getCount(L label) sur une colonne de String donne null
    @Test
    public void testGetAverageOnStringColumnDataFrame(){
        assertNull(filledDF.getAverage("colonne2"));
    }

    //Ce test vérifie que la fonction getCount(L label) sur une colonne de String donne le bon résultat
    @Test
    public void testGetCountOnStringColumnDataFrame(){
        int count = 3;
        assertEquals(filledDF.getCount("colonne2"), count);
    }

    //Ce test vérifie que la fonction getSum(L label) sur une colonne de String donne null
    @Test
    public void testGetSumOnStringColumnDataFrame(){
        assertNull(filledDF.getSum("colonne2"));
    }

    //Ce test vérifie que la fonction getAbsolute(L label) sur colonne de String donne null
    // du dataFrame de la colonne label
    @Test
    public void testAbsoluteMinOnStringColumnDataFrame(){
        assertNull(filledDF.getAbsolute("colonne2"));
    }

    //Ce test vérifie que la fonction getProduct(L label) sur une colonne de String donne null
    @Test
    public void testGetProductOnStringColumnDataFrame(){
        assertNull(filledDF.getProduct("colonne2"));
    }


        //Test sur des char

    //Ce test vérifie que la fonction getCount(L label) sur une colonne de char donne null
    @Test
    public void testGetAverageOnCharColumnDataFrame(){
        assertNull(filledDF.getAverage("colonne3"));
    }

    //Ce test vérifie que la fonction getCount(L label) sur une colonne de char donne le bon résultat
    @Test
    public void testGetCountOnCharColumnDataFrame(){
        int count = 3;
        assertEquals(filledDF.getCount("colonne3"), count);
    }

    //Ce test vérifie que la fonction getSum(L label) sur une colonne de char donne null
    @Test
    public void testGetSumOnCharColumnDataFrame(){
        assertNull(filledDF.getSum("colonne3"));
    }

    //Ce test vérifie que la fonction getAbsolute(L label) sur colonne de char donne null
    // du dataFrame de la colonne label
    @Test
    public void testAbsoluteMinOnCharColumnDataFrame(){
        assertNull(filledDF.getAbsolute("colonne3"));
    }

    //Ce test vérifie que la fonction getProduct(L label) sur une colonne de char donne null
    @Test
    public void testGetProductOnCharColumnDataFrame(){
        assertNull(filledDF.getProduct("colonne3"));
    }


        //Test sur des boolean

    //Ce test vérifie que la fonction getCount(L label) sur une colonne de Boolean donne null
    @Test
    public void testGetAverageOnBooleanColumnDataFrame(){
        assertNull(filledDF.getAverage("colonne4"));
    }

    //Ce test vérifie que la fonction getCount(L label) sur une colonne de Boolean donne le bon résultat
    @Test
    public void testGetCountOnBooleanColumnDataFrame(){
        int count = 3;
        assertEquals(filledDF.getCount("colonne4"), count);
    }

    //Ce test vérifie que la fonction getSum(L label) sur une colonne de Boolean donne null
    @Test
    public void testGetSumOnBooleanColumnDataFrame(){
        assertNull(filledDF.getSum("colonne4"));
    }

    //Ce test vérifie que la fonction getAbsolute(L label) sur colonne de Boolean donne null
    // du dataFrame de la colonne label
    @Test
    public void testAbsoluteMinOnBooleanColumnDataFrame(){
        assertNull(filledDF.getAbsolute("colonne4"));
    }

    //Ce test vérifie que la fonction getProduct(L label) sur une colonne de Boolean donne null
    @Test
    public void testGetProductOnBooleanColumnDataFrame(){
        assertNull(filledDF.getProduct("colonne4"));
    }


        /*Test sur voidDF */

    //Ce test vérifie que la fonction getMin(L label) donne la valeur minimale du dataFrame de la colonne label
    @Test
    public void testGetMinEmptyDataFrame(){
        assertNull(voidDF.getMin("colonne1"));
    }

    //Ce test vérifie que la fonction getMax(L label) donne la valeur maximale du dataFrame de la colonne label
    @Test
    public void testGetMaxEmptyDataFrame(){
        assertNull(voidDF.getMax("colonne1"));
    }

    //Ce test vérifie que la fonction getAverage(L label) donne la valeur moyenne du dataFrame de la colonne label
    @Test
    public void testGetAverageEmptyDataFrame(){
        assertNull(voidDF.getAverage("colonne1"));
    }

    //Ce test vérifie que la fonction getCount(L label) donne le nombre d'entrée non null du dataFrame de la colonne label
    @Test
    public void testGetCountEmptyDataFrame(){
        int count = -1;
        assertEquals(voidDF.getCount("colonne1"), count);
    }

    //Ce test vérifie que la fonction getSum(L label) calcule la somme des entrées non null du dataFrame de la colonne label
    @Test
    public void testGetSumEmptyDataFrame(){
        assertNull(voidDF.getSum("colonne1"));
    }

    //Ce test vérifie que la fonction getAbsolute(L label) calcule la valeur absolue de somme des entrées non null
    // du dataFrame de la colonne label
    @Test
    public void testAbsolutEmptyDataFrame(){
        assertNull(voidDF.getAbsolute("colonne1"));
    }

    //Ce test vérifie que la fonction getProduct(L label) calcule le produit des entrées non null du dataFrame de la colonne label
    @Test
    public void testGetProductEmptyDataFrame(){
        assertNull(voidDF.getProduct("colonne1"));
    }

}
