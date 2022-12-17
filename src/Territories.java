import java.util.ArrayList;

/**
 * Initializes all 42 territories in the game.
 */
public class Territories {
    protected static Territory Alaska,NWTerritory, Greenland, Alberta, Ontario, Quebec,  WesternUS, EasternUS, CentralAmerica, Venezuela, Peru, Brazil, Argentina, NorthAfrica;
    protected static Territory Egypt, EastAfrica, Congo, SouthAfrica, Madagascar, WEurope, Britain, Iceland, Scandinavia, NEurope, SEurope, Ukraine, MiddleEast, Afghanistan;
    protected static Territory Ural, Siberia, Yakutsk, Kamchatka, Irkutsk, Mongolia, China, India, Siam, Indonesia, PapuaNewGuinea, WesternAustralia, EasternAustralia, Japan;
    protected static Territory Yukon, Nunavut, BritishColumbia, Saskatchewan, Manitoba, Labrador, NewBrunswick, NovaScotia, PrinceEdwardIsland;
    protected static ArrayList<Territory> firstMapTerritoryList;
    private static ArrayList<Territory> secondMapTerritoryList;


    /**
     * Initializes all 42 territories.
     */
    public static void createFirstMapTerritories() {
        Alaska = new Territory("Alaska", 1);
        NWTerritory = new Territory("NWTerritory", 1);
        Greenland = new Territory("Greenland", 1);
        Alberta = new Territory("Alberta", 1);
        Ontario = new Territory("Ontario", 1);
        Quebec = new Territory("Quebec", 1);
        WesternUS = new Territory("WesternUS", 1);
        EasternUS = new Territory("EasternUS", 1);
        CentralAmerica = new Territory("CentralAmerica", 1);
        Venezuela = new Territory("Venezuela", 1);
        Peru = new Territory("Peru", 1);
        Brazil = new Territory("Brazil", 1);
        Argentina = new Territory("Argentina", 1);
        NorthAfrica = new Territory("NorthAfrica", 1);
        Egypt = new Territory("Egypt", 1);
        EastAfrica = new Territory("EastAfrica", 1);
        Congo = new Territory("Congo", 1);
        SouthAfrica = new Territory("SouthAfrica", 1);
        Madagascar = new Territory("Madagascar", 1);
        WEurope = new Territory("WEurope", 1);
        Britain = new Territory("Britain", 1);
        Iceland = new Territory("Iceland", 1);
        Scandinavia = new Territory("Scandinavia", 1);
        NEurope = new Territory("NEurope", 1);
        SEurope = new Territory("SEurope", 1);
        Ukraine = new Territory("Ukraine", 1);
        MiddleEast = new Territory("Middle-East", 1);
        Afghanistan = new Territory("Afghanistan", 1);
        Ural = new Territory("Ural", 1);
        Siberia = new Territory("Siberia", 1);
        Yakutsk = new Territory("Yakutsk", 1);
        Kamchatka = new Territory("Kamchatka", 1);
        Irkutsk = new Territory("Irkutsk", 1);
        Mongolia = new Territory("Mongolia", 1);
        China = new Territory("China", 1);
        India = new Territory("India", 1);
        Siam = new Territory("Siam", 1);
        Indonesia = new Territory("Indonesia", 1);
        PapuaNewGuinea = new Territory("PapuaNewGuinea", 1);
        WesternAustralia = new Territory("WesternAustralia", 1);
        EasternAustralia = new Territory("EasternAustralia", 1);
        Japan = new Territory("Japan", 1);
        setFirstMapNeighbors();
    }

    /**
     * Sets neighbours to each territory.
     */
    public static void setFirstMapNeighbors(){
        Alaska.setNeighbor(NWTerritory);
        Alaska.setNeighbor(Alberta);
        Alaska.setNeighbor(Kamchatka);

        NWTerritory.setNeighbor(Alaska);
        NWTerritory.setNeighbor(Alberta);
        NWTerritory.setNeighbor(Ontario);
        NWTerritory.setNeighbor(Greenland);

        Greenland.setNeighbor(NWTerritory);
        Greenland.setNeighbor(Alberta);
        Greenland.setNeighbor(Ontario);
        Greenland.setNeighbor(Iceland);

        Alberta.setNeighbor(NWTerritory);
        Alberta.setNeighbor(Alaska);
        Alberta.setNeighbor(Ontario);
        Alberta.setNeighbor(WesternUS);

        Ontario.setNeighbor(Quebec);
        Ontario.setNeighbor(Alberta);
        Ontario.setNeighbor(NWTerritory);
        Ontario.setNeighbor(Iceland);
        Ontario.setNeighbor(WesternUS);
        Ontario.setNeighbor(EasternUS);

        Quebec.setNeighbor(Ontario);
        Quebec.setNeighbor(Greenland);
        Quebec.setNeighbor(EasternUS);

        WesternUS.setNeighbor(EasternUS);
        WesternUS.setNeighbor(Alberta);
        WesternUS.setNeighbor(Ontario);
        WesternUS.setNeighbor(CentralAmerica);

        EasternUS.setNeighbor(Ontario);
        EasternUS.setNeighbor(Quebec);
        EasternUS.setNeighbor(WesternUS);
        EasternUS.setNeighbor(CentralAmerica);

        CentralAmerica.setNeighbor(WesternUS);
        CentralAmerica.setNeighbor(EasternUS);
        CentralAmerica.setNeighbor(Venezuela);

        Venezuela.setNeighbor(CentralAmerica);
        Venezuela.setNeighbor(Peru);
        Venezuela.setNeighbor(Brazil);

        Peru.setNeighbor(Venezuela);
        Peru.setNeighbor(Brazil);
        Peru.setNeighbor(Argentina);

        Brazil.setNeighbor(Venezuela);
        Brazil.setNeighbor(Peru);
        Brazil.setNeighbor(Argentina);
        Brazil.setNeighbor(NorthAfrica);

        Argentina.setNeighbor(Peru);
        Argentina.setNeighbor(Brazil);

        NorthAfrica.setNeighbor(Brazil);
        NorthAfrica.setNeighbor(WEurope);
        NorthAfrica.setNeighbor(SEurope);
        NorthAfrica.setNeighbor(Egypt);
        NorthAfrica.setNeighbor(EastAfrica);
        NorthAfrica.setNeighbor(Congo);

        Egypt.setNeighbor(SEurope);
        Egypt.setNeighbor(NorthAfrica);
        Egypt.setNeighbor(EastAfrica);
        Egypt.setNeighbor(MiddleEast);

        EastAfrica.setNeighbor(Congo);
        EastAfrica.setNeighbor(Egypt);
        EastAfrica.setNeighbor(NorthAfrica);
        EastAfrica.setNeighbor(Madagascar);
        EastAfrica.setNeighbor(SouthAfrica);
        EastAfrica.setNeighbor(MiddleEast);

        Congo.setNeighbor(NorthAfrica);
        Congo.setNeighbor(EastAfrica);
        Congo.setNeighbor(SouthAfrica);

        SouthAfrica.setNeighbor(Congo);
        SouthAfrica.setNeighbor(EastAfrica);
        SouthAfrica.setNeighbor(Madagascar);

        Madagascar.setNeighbor(EastAfrica);
        Madagascar.setNeighbor(SouthAfrica);

        WEurope.setNeighbor(NorthAfrica);
        WEurope.setNeighbor(SEurope);
        WEurope.setNeighbor(NEurope);
        WEurope.setNeighbor(Britain);

        Britain.setNeighbor(Iceland);
        Britain.setNeighbor(Scandinavia);
        Britain.setNeighbor(NEurope);
        Britain.setNeighbor(WEurope);

        Iceland.setNeighbor(Greenland);
        Iceland.setNeighbor(Scandinavia);
        Iceland.setNeighbor(Britain);

        Scandinavia.setNeighbor(Britain);
        Scandinavia.setNeighbor(NEurope);
        Scandinavia.setNeighbor(Ukraine);
        Scandinavia.setNeighbor(Iceland);


        NEurope.setNeighbor(Scandinavia);
        NEurope.setNeighbor(SEurope);
        NEurope.setNeighbor(Britain);
        NEurope.setNeighbor(WEurope);
        NEurope.setNeighbor(Ukraine);

        SEurope.setNeighbor(Ukraine);
        SEurope.setNeighbor(NEurope);
        SEurope.setNeighbor(WEurope);
        SEurope.setNeighbor(Egypt);
        SEurope.setNeighbor(NorthAfrica);
        SEurope.setNeighbor(MiddleEast);

        Ukraine.setNeighbor(Scandinavia);
        Ukraine.setNeighbor(NEurope);
        Ukraine.setNeighbor(SEurope);
        Ukraine.setNeighbor(Ural);
        Ukraine.setNeighbor(Afghanistan);
        Ukraine.setNeighbor(MiddleEast);

        MiddleEast.setNeighbor(Ukraine);
        MiddleEast.setNeighbor(SEurope);
        MiddleEast.setNeighbor(Egypt);
        MiddleEast.setNeighbor(EastAfrica);
        MiddleEast.setNeighbor(India);
        MiddleEast.setNeighbor(Afghanistan);

        Afghanistan.setNeighbor(Ural);
        Afghanistan.setNeighbor(Ukraine);
        Afghanistan.setNeighbor(MiddleEast);
        Afghanistan.setNeighbor(India);
        Afghanistan.setNeighbor(China);

        Ural.setNeighbor(Ukraine);
        Ural.setNeighbor(Afghanistan);
        Ural.setNeighbor(Siberia);
        Ural.setNeighbor(China);

        Siberia.setNeighbor(Ural);
        Siberia.setNeighbor(Yakutsk);
        Siberia.setNeighbor(Irkutsk);
        Siberia.setNeighbor(Mongolia);
        Siberia.setNeighbor(China);

        Yakutsk.setNeighbor(Siberia);
        Yakutsk.setNeighbor(Irkutsk);
        Yakutsk.setNeighbor(Kamchatka);

        Kamchatka.setNeighbor(Alaska);
        Kamchatka.setNeighbor(Japan);
        Kamchatka.setNeighbor(Yakutsk);
        Kamchatka.setNeighbor(Irkutsk);
        Kamchatka.setNeighbor(Mongolia);

        Irkutsk.setNeighbor(Kamchatka);
        Irkutsk.setNeighbor(Yakutsk);
        Irkutsk.setNeighbor(Siberia);
        Irkutsk.setNeighbor(Mongolia);

        Mongolia.setNeighbor(Irkutsk);
        Mongolia.setNeighbor(Japan);
        Mongolia.setNeighbor(Siberia);
        Mongolia.setNeighbor(China);

        China.setNeighbor(Mongolia);
        China.setNeighbor(Siberia);
        China.setNeighbor(Ural);
        China.setNeighbor(Afghanistan);
        China.setNeighbor(India);
        China.setNeighbor(Siam);

        India.setNeighbor(MiddleEast);
        India.setNeighbor(Afghanistan);
        India.setNeighbor(China);
        India.setNeighbor(Siam);

        Siam.setNeighbor(India);
        Siam.setNeighbor(China);
        Siam.setNeighbor(Indonesia);

        Indonesia.setNeighbor(Siam);
        Indonesia.setNeighbor(PapuaNewGuinea);
        Indonesia.setNeighbor(WesternAustralia);

        PapuaNewGuinea.setNeighbor(Indonesia);
        PapuaNewGuinea.setNeighbor(WesternAustralia);
        PapuaNewGuinea.setNeighbor(EasternAustralia);

        WesternAustralia.setNeighbor(Indonesia);
        WesternAustralia.setNeighbor(EasternAustralia);
        WesternAustralia.setNeighbor(PapuaNewGuinea);

        EasternAustralia.setNeighbor(PapuaNewGuinea);
        EasternAustralia.setNeighbor(EasternAustralia);

        Japan.setNeighbor(Kamchatka);
        Japan.setNeighbor(Mongolia);
    }
    /**
     * Adds all territories to a list. Returns the list.
     * @return
     */
    public static ArrayList<Territory> createFirstMapTerritoryList(){
        firstMapTerritoryList = new ArrayList<>();
        firstMapTerritoryList.add(Alaska);
        firstMapTerritoryList.add(NWTerritory);
        firstMapTerritoryList.add(Greenland);
        firstMapTerritoryList.add(Alberta);
        firstMapTerritoryList.add(Ontario);
        firstMapTerritoryList.add(Quebec);
        firstMapTerritoryList.add(WesternUS);
        firstMapTerritoryList.add(EasternUS);
        firstMapTerritoryList.add(CentralAmerica);
        firstMapTerritoryList.add(Venezuela);
        firstMapTerritoryList.add(Peru);
        firstMapTerritoryList.add(Brazil);
        firstMapTerritoryList.add(Argentina);
        firstMapTerritoryList.add(NorthAfrica);
        firstMapTerritoryList.add(Egypt);
        firstMapTerritoryList.add(EastAfrica);
        firstMapTerritoryList.add(Congo);
        firstMapTerritoryList.add(SouthAfrica);
        firstMapTerritoryList.add(Madagascar);
        firstMapTerritoryList.add(WEurope);
        firstMapTerritoryList.add(Britain);
        firstMapTerritoryList.add(Iceland);
        firstMapTerritoryList.add(Scandinavia);
        firstMapTerritoryList.add(NEurope);
        firstMapTerritoryList.add(SEurope);
        firstMapTerritoryList.add(Ukraine);
        firstMapTerritoryList.add(MiddleEast);
        firstMapTerritoryList.add(Afghanistan);
        firstMapTerritoryList.add(Ural);
        firstMapTerritoryList.add(Siberia);
        firstMapTerritoryList.add(Yakutsk);
        firstMapTerritoryList.add(Kamchatka);
        firstMapTerritoryList.add(Irkutsk);
        firstMapTerritoryList.add(Mongolia);
        firstMapTerritoryList.add(China);
        firstMapTerritoryList.add(India);
        firstMapTerritoryList.add(Siam);
        firstMapTerritoryList.add(Indonesia);
        firstMapTerritoryList.add(PapuaNewGuinea);
        firstMapTerritoryList.add(WesternAustralia);
        firstMapTerritoryList.add(EasternAustralia);
        firstMapTerritoryList.add(Japan);
        return firstMapTerritoryList;
    }

    /**
     * Creates Canada territories.
     */
    public static void createSecondMapTerritories(){
        Alaska = new Territory("Alaska", 1);
        Yukon = new Territory("Yukon", 1);
        NWTerritory = new Territory("NWTerritory", 1);
        Nunavut = new Territory("Nunavut", 1);
        BritishColumbia = new Territory("BritishColumbia", 1);
        Alberta = new Territory("Alberta", 1);
        Saskatchewan = new Territory("Saskatchewan", 1);
        Manitoba = new Territory("Manitoba", 1);
        Ontario = new Territory("Ontario", 1);
        Quebec = new Territory("Quebec", 1);
        Labrador = new Territory("Labrador", 1);
        NewBrunswick = new Territory("NewBrunswick", 1);
        NovaScotia = new Territory("NovaScotia", 1);
        PrinceEdwardIsland = new Territory("PrinceEdwardIsland", 1);
        setSecondMapNeighbors();
    }

    /**
     * Sets all neighbours for Canada map.
     */
    public static void setSecondMapNeighbors(){
        Alaska.setNeighbor(Yukon);
        Alaska.setNeighbor(BritishColumbia);

        Yukon.setNeighbor(NWTerritory);
        Yukon.setNeighbor(BritishColumbia);
        Yukon.setNeighbor(Alaska);

        NWTerritory.setNeighbor(Yukon);
        NWTerritory.setNeighbor(Alberta);
        NWTerritory.setNeighbor(BritishColumbia);
        NWTerritory.setNeighbor(Saskatchewan);
        NWTerritory.setNeighbor(Nunavut);

        Nunavut.setNeighbor(NWTerritory);
        Nunavut.setNeighbor(Quebec);
        Nunavut.setNeighbor(Manitoba);
        Nunavut.setNeighbor(Ontario);

        BritishColumbia.setNeighbor(Yukon);
        BritishColumbia.setNeighbor(NWTerritory);
        BritishColumbia.setNeighbor(Alberta);
        BritishColumbia.setNeighbor(Alaska);

        Alberta.setNeighbor(BritishColumbia);
        Alberta.setNeighbor(NWTerritory);
        Alberta.setNeighbor(Saskatchewan);

        Saskatchewan.setNeighbor(Alberta);
        Saskatchewan.setNeighbor(NWTerritory);
        Saskatchewan.setNeighbor(Manitoba);

        Manitoba.setNeighbor(Saskatchewan);
        Manitoba.setNeighbor(Nunavut);
        Manitoba.setNeighbor(Ontario);

        Ontario.setNeighbor(Manitoba);
        Ontario.setNeighbor(Nunavut);
        Ontario.setNeighbor(Quebec);

        Quebec.setNeighbor(Ontario);
        Quebec.setNeighbor(Nunavut);
        Quebec.setNeighbor(Labrador);
        Quebec.setNeighbor(NewBrunswick);

        Labrador.setNeighbor(Quebec);
        Labrador.setNeighbor(Nunavut);
        Labrador.setNeighbor(NovaScotia);

        NewBrunswick.setNeighbor(Quebec);
        NewBrunswick.setNeighbor(NovaScotia);
        NewBrunswick.setNeighbor(PrinceEdwardIsland);

        NovaScotia.setNeighbor(NewBrunswick);
        NovaScotia.setNeighbor(Labrador);
        NovaScotia.setNeighbor(PrinceEdwardIsland);

        PrinceEdwardIsland.setNeighbor(NovaScotia);
        PrinceEdwardIsland.setNeighbor(NewBrunswick);
    }

    /**
     * Creates a list of all territories in the Canada map.
     * @return
     */
    public static ArrayList<Territory> createSecondMapTerritoryList(){
        secondMapTerritoryList = new ArrayList<>();
        secondMapTerritoryList.add(Alaska);
        secondMapTerritoryList.add(Yukon);
        secondMapTerritoryList.add(NWTerritory);
        secondMapTerritoryList.add(Nunavut);
        secondMapTerritoryList.add(Labrador);
        secondMapTerritoryList.add(PrinceEdwardIsland);
        secondMapTerritoryList.add(BritishColumbia);
        secondMapTerritoryList.add(Alberta);
        secondMapTerritoryList.add(Saskatchewan);
        secondMapTerritoryList.add(Manitoba);
        secondMapTerritoryList.add(Ontario);
        secondMapTerritoryList.add(Quebec);
        secondMapTerritoryList.add(NewBrunswick);
        secondMapTerritoryList.add(NovaScotia);
        return secondMapTerritoryList;
    }
}

