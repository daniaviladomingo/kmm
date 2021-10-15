//
//  CharactersView.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 15/9/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharactersView: View {    
    @ObservedObject var viewModel: CharactersVM = CharactersVM()
    
    init() {
        viewModel.setEvent(event: CharactersContractEvent.OnGetCharacters.shared)
    }

    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.listCharacters, id: \.self) { character in
                    NavigationLink(destination: CharacterDetailView(character: character)) {
                        ItemCharacterView(character: character)
                    }
                }
            }
            .navigationBarItems(trailing: NavigationLink(destination: CharactersFavoritesView()){
                Image(systemName: "star.fill")
                    .frame(alignment: .center)
            })
            .navigationTitle(Text("Rick & Morty KMM"))
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct CharactersView_Previews: PreviewProvider {
    static var previews: some View {
        CharactersView()
    }
}
