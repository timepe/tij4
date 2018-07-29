package chapter14.typeinfo.factory;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Part {
    public String toString() {
        return getClass().getSimpleName();
    }

//    static List<Factory<? extends Part>> partFactories =
//            new ArrayList<Factory<? extends Part>>();
      static List<Class<? extends Factory>> partFactories =
            new ArrayList<Class<? extends Factory>>();


    static {
        partFactories.add(FuelFilter.Factory.class);
        partFactories.add(AirFilter.Factory.class);
        partFactories.add(CabinAirFilter.Factory.class);
        partFactories.add(OilFilter.Factory.class);
        partFactories.add(FanBelt.Factory.class);
        partFactories.add(PowerSteeringBelt.Factory.class);
        partFactories.add(GeneratorBelt.Factory.class);
    }

    private static Random rand = new Random(47);
    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        try {
            Factory<Part> factory = (Factory<Part>)partFactories.get(n).newInstance();
            return factory.create();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

class Filter extends Part {}

class FuelFilter extends Filter {
    public static class Factory implements chapter14.typeinfo.factory.Factory<FuelFilter> {
        public FuelFilter create() { return new FuelFilter(); }
    }
}

class AirFilter extends Filter {
    public static class Factory implements chapter14.typeinfo.factory.Factory<AirFilter> {
        public AirFilter create() { return new AirFilter(); }
    }
}

class CabinAirFilter extends Filter {
    public static class Factory implements chapter14.typeinfo.factory.Factory<CabinAirFilter> {
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}

class OilFilter extends Filter {
    public static class Factory implements chapter14.typeinfo.factory.Factory<OilFilter> {
        public OilFilter create() {
            return new OilFilter();
        }
    }
}

class Belt extends Part {}

class FanBelt extends Belt {
    public static class Factory implements chapter14.typeinfo.factory.Factory<FanBelt> {
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

class GeneratorBelt extends Belt {
    public static class Factory implements chapter14.typeinfo.factory.Factory<GeneratorBelt> {
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}

class PowerSteeringBelt extends Belt {
    public static class Factory implements chapter14.typeinfo.factory.Factory<PowerSteeringBelt> {
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}

public class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Part.createRandom());
        }
    }
}
