package loanSysModel.managers;

import loanSysModel.Member;
import java.util.ArrayList;
import java.util.List;

// Denna klass hanterar alla medlemmar i systemet – likt hur ProductManager hanterar produkter.
public class MemberManager {
    private List<Member> members = new ArrayList<>();

    private int MemberId = 1; // unikt ID för varje medlem

    // Lägger till en ny testmedlem med automatiskt ID
    public Member addNewTestMember() {
        Member member = new Member(MemberId, "MemberId: " + MemberId);
        members.add(member);
        MemberId++;
        return member;
    }

    // Lägger till flera testmedlemmar vid uppstart
    public void addTestMembers() {
        for (int i = 0; i < 25; i++) {
            addNewTestMember();
        }
    }

    // Hämtar medlem via index – används t.ex. för att slumpa fram en medlem
    public Member get(int index) {
        if (index >= 0 && index < members.size()) {
            return members.get(index);
        }
        return null;
    }

    // Returnerar antal medlemmar
    public int size() {
        return members.size();
    }
}
