package sample;

import java.util.Random;

class Guest extends Player {
    private String formatName;


    public String GetName() {
        String name = this.formatName;
        return name;
    }


    private Character[] randomName = {
            '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            '@', '$', '!', '&',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    Random random = new Random();


    Guest() {
        super(0, 0);

        StringBuilder result = new StringBuilder();

        // Select random characters
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(randomName.length);
            result.append(randomName[randomIndex]);
        }

        formatName = "Guestplayer-" + result;
    }


}
