//Dropbox, Inc. --> CODE CHALLENGE

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FoldersAndCows{
  int id = 0, in = 0, out = 0;
  boolean access = false;
  List<FoldersAndCows> children = new ArrayList<>();
  List<Integer> cows = new ArrayList<>();

  FoldersAndCows(int id, boolean access){
    this.id = id;
    this.access = access;
    in = out = 0;
  }

  boolean appendCow(List<Integer> newCows){
    return cows.addAll(newCows);
  }
}

class FolderAndCow{

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    List<FoldersAndCows> folders = new ArrayList<>();

    int numLeaf = 0;
    int Q = scanner.nextInt();
    int M = scanner.nextInt();
    int N = scanner.nextInt();
    while(M-- > 0){
      FoldersAndCows f = new FoldersAndCows(scanner.nextInt(), true);
      folders.add(f);
      int K = scanner.nextInt();
      while(K-- > 0) {
        f.cows.add(scanner.nextInt());
      }
    }
    while(N-- > 0){
      FoldersAndCows f = new FoldersAndCows(scanner.nextInt(), false);
      folders.add(f);
      int K = scanner.nextInt();
      while(K-- > 0)
        f.cows.add(scanner.nextInt());
    }

    int G = scanner.nextInt();
    while(G-- > 0){
      int U = scanner.nextInt();
      int V = scanner.nextInt();
      FoldersAndCows u = findFolder(folders, U);
      FoldersAndCows v = findFolder(folders, V);
      if(u != null & v != null){
        u.children.add(v);
        u.out++;
        v.in++;
      }
    }

    int[] cows = new int[Q];

    for(final FoldersAndCows item : folders){
      if (item.in == 0) {
        traverse(cows, item);
      }
      if(item.out == 0) {
        numLeaf++;
      }
    }

    List<Integer> res = new ArrayList<>();
    for(int i = 0; i < Q; i++)
      if(cows[i] < numLeaf)
        res.add(i);

    for(final int i: res) {
      System.out.print(i + " ");
    }
  }

  private static FoldersAndCows findFolder(List<FoldersAndCows> folders, int id){
    for(FoldersAndCows folder: folders)
      if(folder.id == id)
        return folder;
    return null;
  }

  private static void traverse(int[] cows, FoldersAndCows root){
    if(root.out == 0) {
      for (int i : root.cows) {
        cows[i]++;
      }
    }
    for(FoldersAndCows f: root.children){
      if(f.access) {
        f.appendCow(root.cows);
      }
      traverse(cows, f);
    }
  }
}