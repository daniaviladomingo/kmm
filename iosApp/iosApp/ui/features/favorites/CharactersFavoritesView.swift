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
        ManagementResourceState<NSArray, CharactersListView>(
            resourceState: viewModel.state.charactersFavorites,
            successView: { charactersFavorites in
                CharactersListView(characters: charactersFavorites as! [Character])
            },
            onTryAgain: { viewModel.setEvent(event: CharactersFavoritesContractEventOnGetCharactersFavorites.shared) },
            onCheckAgain: { viewModel.setEvent(event: CharactersFavoritesContractEventOnGetCharactersFavorites.shared) },
            msgCheckAgain: "You don't favorite characters yet"
        )
        .navigationTitle(Text("Characters Favorites"))
    }
}

struct CharactersFavoritesView_Previews: PreviewProvider {
    static var previews: some View {
        CharactersFavoritesView()
    }
}
