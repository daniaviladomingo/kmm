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
   
    var body: some View {
        NavigationView {
            ManagementResourceState<NSArray, CharactersListView>(
                resourceState: viewModel.state.characters,
                successView: { characters in
                    CharactersListView(characters: characters as! [Character])
                },
                onTryAgain: { viewModel.setEvent(event: CharactersContractEventOnGetCharacters.shared) },
                onCheckAgain: { viewModel.setEvent(event: CharactersContractEventOnGetCharacters.shared) }
            )
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
