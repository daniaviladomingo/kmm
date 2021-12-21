//
//  CharacersFavoritesView.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 15/9/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CharactersFavoritesView: View {
    @ObservedObject var viewModel: CharactersFavoritesVM = CharactersFavoritesVM()
    
    var body: some View {
        VStack {
            if (!viewModel.listCharactersFavorites.isEmpty) {
                List {
                    ForEach(viewModel.listCharactersFavorites, id: \.self) { character in
                        NavigationLink(destination: CharacterDetailView(characterId: Int(character.id))) {
                            ItemCharacterView(character: character)
                        }
                    }
                }
            } else {
                Text("You don't favorite characters yet")
                    .bold()
                    .frame(alignment: .init(horizontal: .center, vertical: .center))
            }
        }
        .navigationTitle(Text("Characters Favorites"))
        .onAppear(perform: {
            viewModel.setEvent(event:CharactersFavoritesContractEvent.OnGetCharactersFavorites.shared)
        })
    }
}

struct CharactersFavoritesView_Previews: PreviewProvider {
    static var previews: some View {
        CharactersFavoritesView()
    }
}
