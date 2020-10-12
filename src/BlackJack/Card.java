public class Card {
    // 1,2,3...13
    // 1 = A, 11 is J, 12 = Q, 13 = K
    public int rank;
    // 'H', 'D', 'C', 'S'
    public char suit;

    public Card(int rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        String rank = "";
        if (this.rank == 1) {
            rank = "Ace";
        } else if (this.rank < 11) {
            rank = String.valueOf(this.rank);
        } else if (this.rank == 11) {
            rank = "Jack";
        } else if (this.rank == 12) {
            rank = "Queen";
        } else if (this.rank == 13) {
            rank = "King";
        }
        return rank;
    }

    public String getSuit() {
        String suit = "";
        if (this.suit == 'H') {
            suit = "Hearts";
        } else if (this.suit == 'D') {
            suit = "Diamonds";
        } else if (this.suit == 'C') {
            suit = "Clubs";
        } else if (this.suit == 'S') {
            suit = "Spades";
        }
        return suit;
    }
}
