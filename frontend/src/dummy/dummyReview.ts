import DefaultReviewImage1 from '@assets/image/default-review-image1.jpg'
import DefaultReviewImage2 from '@assets/image/default-review-image2.jpg'
import DefaultReviewImage3 from '@assets/image/default-review-image3.jpg'
import DefaultReviewImage4 from '@assets/image/default-review-image4.jpg'
import DefaultReviewImage5 from '@assets/image/default-review-image5.jpg'
import DefaultReviewImage6 from '@assets/image/default-review-image6.jpg'
import DefaultReviewImage7 from '@assets/image/default-review-image7.jpg'
import DefaultReviewImage8 from '@assets/image/default-review-image8.jpg'
import DefaultReviewImage9 from '@assets/image/default-review-image9.jpg'

export const Reviews = [
  {
    id: 1,
    mainImage: DefaultReviewImage1,
    images: [DefaultReviewImage1, DefaultReviewImage4, DefaultReviewImage7],
    title: '캐스타와 함께한 은하수 여행',
    tag: 'NICE_PHOTOS',
    author: '별빛속캐스타별헤는밤',
    content:
      '드디어 은하수를 직접 눈으로 확인하고 사진으로 남길 수 있었어요. \n 별들이 하늘 가득 반짝이는 순간을 보고 있으면 시간도 공간도 잊게 되더라구요. 보정을 조금 하긴 했지만, 그래도 은하수의 그 찬란한 아름다움은 감출 수 없네요!\n\n 이 경험은 제 인생에 있어 잊지 못할 추억으로 남을 것 같아요.',
    addressSummary: '경기도 연천군 연천읍',
    date: '2024.10.01',

    reactionCount: {
      visitAgain: 9,
      helpful: 87,
      nicePhotos: 999,
    },
  },
  {
    id: 2,
    mainImage: DefaultReviewImage2,
    images: [DefaultReviewImage2, DefaultReviewImage5, DefaultReviewImage8],
    title: '은하수 촬영 도전기, 성공했어요!',
    tag: 'HELPFUL',
    author: '캐스타밤하늘정복자빛의여정',
    content:
      '처음으로 은하수를 찍어보았는데, 생각보다 어려웠어요. 노출 조절과 포커스를 맞추는 과정이 꽤 까다로웠지만, 찍힌 결과물을 보니 그 모든 노력이 보상받는 기분이었어요.\n 여러분도 은하수 촬영에 도전해보세요. 팁을 드리자면, 날씨와 빛 공해가 적은 장소를 고르는 게 중요해요!\n\n 이 작은 성공이 저에게 큰 자신감을 주었어요.',
    addressSummary: '경북 영양군 수비면',
    date: '2024.10.02',
    reactionCount: {
      visitAgain: 1,
      helpful: 99,
      nicePhotos: 9,
    },
  },
  {
    id: 3,
    mainImage: DefaultReviewImage3,
    images: [DefaultReviewImage3, DefaultReviewImage6, DefaultReviewImage9],
    title: '은하수와 별똥별을 동시에!',
    tag: 'VISIT_AGAIN',
    author: '하늘속의길캐스타',
    content:
      '은하수를 보러 갔던 날, 뜻밖에 별똥별까지 볼 수 있었어요. 정말 꿈같은 경험이었습니다.\n 사진으로 담지는 못했지만, 그 순간을 잊을 수 없을 거예요. 이 장소는 다시 꼭 와보고 싶네요.\n\n 밤하늘을 사랑하는 모든 분들께 추천드려요! 별똥별을 보며 소원도 빌었답니다. 그 소원이 이루어지길 바라요.',
    addressSummary: '충남 태안군 고남면',
    date: '2024.10.03',
    reactionCount: {
      visitAgain: 99,
      helpful: 9,
      nicePhotos: 1,
    },
  },
  {
    id: 4,
    mainImage: DefaultReviewImage4,
    images: [DefaultReviewImage4, DefaultReviewImage7, DefaultReviewImage1],
    title: '별빛 가득한 밤하늘의 낭만',
    tag: 'NICE_PHOTOS',
    author: '캐스타천체관측러버',
    content:
      '하늘을 바라보고 있으면 모든 고민이 사라지는 것 같아요. 카메라에 담긴 별빛들이 너무나도 아름다웠습니다. 다음에는 더 좋은 카메라로 다시 찍고 싶어요.\n 꼭 다시 오고 싶은 장소입니다!\n 이런 순간들이 삶에 있어 진정한 행복을 느끼게 해줍니다.',
    addressSummary: '충북 제천시 한수면',
    date: '2024.10.04',
    reactionCount: {
      visitAgain: 19,
      helpful: 7,
      nicePhotos: 93,
    },
  },
  {
    id: 5,
    mainImage: DefaultReviewImage5,
    images: [DefaultReviewImage5, DefaultReviewImage8, DefaultReviewImage2],
    title: '천문학자의 꿈을 이뤘습니다!',
    tag: 'HELPFUL',
    author: '어린왕자캐스타우주탐험가',
    content:
      '어릴 적부터 별과 우주에 대한 관심이 많았는데, 이번 천체 관측 여행을 통해 꿈을 이루게 됐어요.\n 별자리를 하나하나 찾아보며 천문학자의 기분을 잠시나마 느꼈습니다.\n 다음엔 더 많은 별을 알아보고 싶어요.',
    addressSummary: '강원 정선군 신동읍',
    date: '2024.10.05',
    reactionCount: {
      visitAgain: 7,
      helpful: 93,
      nicePhotos: 19,
    },
  },
  {
    id: 6,
    mainImage: DefaultReviewImage6,
    images: [DefaultReviewImage6, DefaultReviewImage9, DefaultReviewImage3],
    title: '구름 없는 밤, 은하수 포착 성공!',
    tag: 'HELPFUL',
    author: '맑은하늘바라기캐스타',
    content:
      '구름이 없는 맑은 밤, 드디어 은하수를 완벽하게 찍을 수 있었어요!\n 장비를 세팅하는 데 시간이 좀 걸렸지만, 그 보람이 있었습니다. 처음에는 초점 맞추는 게 어려웠는데, 조금만 연습하면 쉽게 성공할 수 있어요.\n 카메라 셋팅 팁은 셔터 스피드를 길게 하고 ISO 값을 낮추는 거예요! 이 멋진 경험을 다른 사람들과도 공유하고 싶어요.',
    addressSummary: '강원 평창군 미탄면',
    date: '2024.10.06',
    reactionCount: {
      visitAgain: 93,
      helpful: 19,
      nicePhotos: 7,
    },
  },
  {
    id: 7,
    mainImage: DefaultReviewImage7,
    images: [DefaultReviewImage7, DefaultReviewImage1, DefaultReviewImage4],
    title: '가족과 함께한 첫 은하수 관측',
    tag: 'VISIT_AGAIN',
    author: '은하수연구소의캐스타',
    content:
      '가족과 함께 은하수를 처음으로 관측했어요.\n 평소에는 도시의 빛 공해 때문에 별을 보기 힘들었지만, 이곳에서는 정말 선명하게 보였습니다. 사진으로도 남겨서 가족들과 함께 추억을 간직할 수 있었어요.\n\n 다시 이곳에 와서 더 많은 별을 보고 싶어요! 이 특별한 순간은 가족 모두에게 소중한 기억으로 남을 것입니다.',
    addressSummary: '충남 부여군 임천면',
    date: '2024.10.07',
    reactionCount: {
      visitAgain: 99,
      helpful: 9,
      nicePhotos: 3,
    },
  },
  {
    id: 8,
    mainImage: DefaultReviewImage8,
    images: [DefaultReviewImage8, DefaultReviewImage2, DefaultReviewImage5],
    title: '별빛과 함께한 캠핑',
    tag: 'VISIT_AGAIN',
    author: '별빛의여정캐스타캠핑러버',
    content:
      '은하수를 보며 캠핑을 즐길 수 있었던 이번 여행은 정말 특별했어요.\n 텐트 밖으로 나와 하늘을 올려다보니 수많은 별들이 가득했어요. 사진은 물론, 직접 눈으로 보는 그 순간이 더 아름다웠답니다.\n\n 다음에도 꼭 다시 와서 이 경험을 반복하고 싶어요. 밤하늘 아래에서의 캠핑은 정말 마법 같은 경험이었어요.',
    addressSummary: '강원 강릉시 왕산면',
    date: '2024.10.08',
    reactionCount: {
      visitAgain: 3,
      helpful: 9,
      nicePhotos: 99,
    },
  },
  {
    id: 9,
    mainImage: DefaultReviewImage9,
    images: [DefaultReviewImage9, DefaultReviewImage3, DefaultReviewImage6],
    title: '별이 쏟아지는 밤하늘 속으로',
    tag: 'NICE_PHOTOS',
    author: '은하의길캐스타별이빛나는밤',
    content:
      '이번에 찍은 사진은 정말 제가 찍은 사진 중 최고였어요.\n 별들이 하늘에 가득 차 있고, 은하수의 흐름이 선명하게 보였습니다.\n 사진을 보고 있으면, 그 순간의 아름다움이 그대로 떠올라요. 다음에도 더 좋은 사진을 남기고 싶습니다!\n\n 이런 순간들이 있기에 천체 사진 촬영은 항상 새롭고 매력적입니다.',
    addressSummary: '경기 가평군 북면',
    date: '2024.10.09',
    reactionCount: {
      visitAgain: 99,
      helpful: 3,
      nicePhotos: 9,
    },
  },
]

export const activeTagMap: { [key: string]: { tag: string; label: string; color: string } } = {
  '1': { tag: 'NICE_PHOTOS', label: '사진이 예뻐요', color: 'text-comp3-light' },
  '2': { tag: 'HELPFUL', label: '도움이 됐어요', color: 'text-comp1-light' },
  '3': { tag: 'VISIT_AGAIN', label: '가보고 싶어요', color: 'text-comp2-light' },
}
