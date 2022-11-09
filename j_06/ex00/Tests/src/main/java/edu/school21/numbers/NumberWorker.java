package edu.school21.numbers;

    public class NumberWorker {
        public boolean isPrime(int number) {
            if (number < 2) {
                throw new IllegalNumberException("Illegal message");
            }
            else if (number == 2 ||  number == 3){
                return true;
            }
            for (       int check = 2; check  <= (int)Math.sqrt(number); check++) {
                if (number % check == 0) {
                    return false;
                }
            }
            return true;
        }

        public int digitsSum(int number) {
            int num = 0;
            while (number > 0) {
                num += number % 10;
                number = number/10;
            }
            return  num;
        }
    }


class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(String message) {
        super(message);
    }
}


