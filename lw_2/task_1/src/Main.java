public class Main {
    public static void main(String[] args) {
        if (!isCorrectArgsNumber(args)) {
            System.out.println("incorrect number of arguments");
            System.exit(0);
        }
        if (!isInputDataCorrect(args[0], args[1])) {
            System.out.println("uncorrect data");
            System.exit(0);
        }

        int[] ip_address = getInputData(args[0]);
        int[] mask = getInputData(args[1]);
        System.out.println(getNetAdress(ip_address, mask));
    }

    private static boolean isInputDataCorrect(String arg1, String arg2) {
        return (arg1.split("\\.").length == 4 && arg2.split("\\.").length == 4);
    }

    private static boolean isCorrectArgsNumber(String[] args) {
        return (args.length == 2);
    }

    private static int[] getInputData(String inputData) {
        String[] str = inputData.split("\\.");
        int[] arr = new int[4];

        for (int i = 0; i < str.length; i++) {
            arr[i] += Integer.parseInt(str[i]);
        }

        return arr;
    }

    private static String getNetAdress(int[] ip, int[] mask) {
        return (
                getAddressPart(ip[0], mask[0]) + '.'
                        + getAddressPart(ip[1], mask[1]) + '.'
                        + getAddressPart(ip[2], mask[2]) + '.'
                        + getAddressPart(ip[3], mask[3])
        );
    }

    private static String getAddressPart(int ip, int mask) {
        return Integer.toString(ip & mask);
    }
}
