//
//  ItemCharacterView.swift
//  iosApp
//
//  Created by Daniel Ávila Domingo on 15/9/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ItemCharacterView: View {
    
    var character: Character
    
    var body: some View {
        HStack {
            AsyncImage(url: URL(string: character.image)) { image in
                image
                    .resizable()
                    .cornerRadius(20)
                    .frame(width: 100, height: 100, alignment: .center)
            } placeholder: {
                ProgressView()
                    .frame(width: 100, height: 100, alignment: .center)
            }
            Text(character.name)
        }
    }
}

struct ItemCharacterView_Previews: PreviewProvider {
    static var previews: some View {
        ItemCharacterView(character: Character(id: 0, name: "Daniel", status: Status.alive, species: "Human", gender: Gender.male, origin: "Belmonte de Tajo", location: "Iceland", image: ""))
    }
}
